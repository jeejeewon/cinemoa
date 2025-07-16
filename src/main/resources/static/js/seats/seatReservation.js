document.addEventListener("DOMContentLoaded", function () {
    const personButtons = document.querySelectorAll(".person-count button");
    const seatElements = document.querySelectorAll(".seat");
    const selectedSeatsList = document.querySelector(".selected-seats ul");
    const priceDisplay = document.querySelector(".price-info p span");

    const seatPrice = {
        성인: 14000,
        청소년: 10000,
        경로: 8000,
        우대: 7000,
    };

    let selectedSeats = []; // 선택된 좌석
    let selectedPeople = {
        성인: 0,
        청소년: 0,
        경로: 0,
        우대: 0,
    };

    // 1. 인원 수 증감 로직
    personButtons.forEach((btn) => {
        btn.addEventListener("click", function () {
            const input = this.parentElement.querySelector("input");
            const label = this.closest(".person-selection-inner").querySelector("span").innerText;
            let value = parseInt(input.value);

            if (this.innerText === "+" && value < 10) {
                value++;
            } else if (this.innerText === "-" && value > 0) {
                value--;
            }
            input.value = value;
            selectedPeople[label] = value;

            updateSeatLimit();
            updatePrice();
        });
    });

    // 총 인원 수 계산
    function getTotalPeopleCount() {
        return Object.values(selectedPeople).reduce((a, b) => a + b, 0);
    }

    // 2. 좌석 선택 로직
    seatElements.forEach((seat) => {
        seat.addEventListener("click", function () {
            if (
                seat.classList.contains("reserved") ||
                seat.classList.contains("disabled")
            ) return;

            // 장애인석 예외 처리
            if (seat.classList.contains("vip") && selectedPeople["우대"] === 0) {
                alert("장애인석은 우대 인원만 선택할 수 있습니다.");
                return;
            }

            const seatLabel = getSeatLabel(seat);

            if (seat.classList.contains("selected")) {
                // 선택 해제
                seat.classList.remove("selected");
                selectedSeats = selectedSeats.filter((s) => s !== seatLabel);
            } else {
                if (selectedSeats.length >= getTotalPeopleCount()) {
                    alert("선택한 인원 수보다 많은 좌석을 선택할 수 없습니다.");
                    return;
                }
                seat.classList.add("selected");
                selectedSeats.push(seatLabel);
            }

            renderSelectedSeats();
            updatePrice();
        });
    });

    // 선택 좌석 리스트 렌더링
    function renderSelectedSeats() {
        selectedSeatsList.innerHTML = "";
        selectedSeats.forEach((seat) => {
            const li = document.createElement("li");
            li.textContent = seat;
            selectedSeatsList.appendChild(li);
        });
    }

    // 좌석 라벨 생성 함수
    function getSeatLabel(seat) {
        const row = seat.closest(".row").dataset.row;
        const number = seat.textContent.trim();
        return `${row}${number}`;
    }

    // 3. 금액 계산 및 표시
    function updatePrice() {
        let total = 0;
        let count = 0;

        for (const type in selectedPeople) {
            const peopleCount = selectedPeople[type];
            for (let i = 0; i < peopleCount; i++) {
                if (count < selectedSeats.length) {
                    total += seatPrice[type];
                    count++;
                }
            }
        }

        priceDisplay.textContent = total.toLocaleString();
        // 세션에 저장 (필요 시 localStorage 등 활용 가능)
        sessionStorage.setItem("selectedSeats", JSON.stringify(selectedSeats));
        sessionStorage.setItem("selectedPeople", JSON.stringify(selectedPeople));
        sessionStorage.setItem("totalPrice", total);
    }

    // 선택 좌석 수보다 많으면 선택 못하게 제한
    function updateSeatLimit() {
        if (selectedSeats.length > getTotalPeopleCount()) {
            alert("인원 수가 줄어들어 좌석 일부가 선택 해제됩니다.");
            while (selectedSeats.length > getTotalPeopleCount()) {
                const seatLabel = selectedSeats.pop();
                const seat = Array.from(seatElements).find((s) => getSeatLabel(s) === seatLabel);
                seat.classList.remove("selected");
            }
            renderSelectedSeats();
            updatePrice();
        }
    }

    // 4. 선택불가 좌석 X 아이콘 스타일 (CSS로 표현)
    document.querySelectorAll('.seat.disabled').forEach(seat => {
        seat.innerHTML = "✕";
        seat.style.color = "#999";
        seat.style.fontWeight = "bold";
        seat.style.fontSize = "16px";
    });

    // 5. 초기화 버튼 기능
    document.querySelector('.reset-btn').addEventListener('click', () => {
        document.querySelectorAll('.person-count input').forEach(input => input.value = 0);
        for (let key in selectedPeople) selectedPeople[key] = 0;

        document.querySelectorAll('.seat.selected').forEach(seat => seat.classList.remove('selected'));
        selectedSeats = [];
        renderSelectedSeats();
        updatePrice();
    });


});
