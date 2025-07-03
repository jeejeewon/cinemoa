document.addEventListener('DOMContentLoaded', () => {
  const menuItems = document.querySelectorAll('.main-nav > ul > li');

  menuItems.forEach(item => {
    const submenu = item.querySelector('.submenu');

    if (submenu) {
      item.addEventListener('mouseenter', () => {
        submenu.style.display = 'block';

        // 전체 서브메뉴를 화면 왼쪽 끝부터 시작하게 조정
        const rect = item.getBoundingClientRect();
        submenu.style.left = `-${rect.left}px`;

        // submenu-inner를 해당 메뉴 항목 가운데로 이동
        const submenuInner = submenu.querySelector('.submenu-inner');
        if (submenuInner) {
          const itemCenter = rect.left + rect.width / 2;
          submenuInner.style.transform = `translateX(calc(${itemCenter}px - 50%))`;
        }
      });

      item.addEventListener('mouseleave', () => {
        submenu.style.display = 'none';

        // 리셋
        submenu.style.left = '0';
        const submenuInner = submenu.querySelector('.submenu-inner');
        if (submenuInner) {
          submenuInner.style.transform = 'translateX(0)';
        }
      });
    }
  });
});
