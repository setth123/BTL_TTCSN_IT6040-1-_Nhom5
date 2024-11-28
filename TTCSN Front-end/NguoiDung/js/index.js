const navbar = document.querySelector('nav');
navbar.addEventListener('click', (event) => {
    if (event.target.tagName === 'a') {
        event.preventDefault();
        const href = event.target.href;
        window.location.href = href;
    }
});