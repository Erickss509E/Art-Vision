// Atualização do JavaScript principal para incluir a página de cadastro

document.addEventListener('DOMContentLoaded', function() {
    // Menu hamburger para dispositivos móveis
    const menuHamburger = document.querySelector('.menu-hamburger');
    const navLinks = document.querySelector('.nav-links');

    if (menuHamburger) {
        menuHamburger.addEventListener('click', function() {
            navLinks.classList.toggle('active');

            // Animação do hamburger
            const spans = menuHamburger.querySelectorAll('span');
            spans.forEach(span => span.classList.toggle('active'));
        });
    }

    // Formulário de contato
    const contactForm = document.getElementById('contactForm');
    if (contactForm) {
        contactForm.addEventListener('submit', function(e) {
            e.preventDefault();
            alert('Mensagem enviada com sucesso! Entraremos em contato em breve.');
            contactForm.reset();
        });
    }

    // Formulário de login
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault();
            // Aqui seria a integração com o backend
            window.location.href = '/sistema/login'; // Redireciona para o sistema
        });
    }

    // Formulário de cadastro
    const cadastroForm = document.getElementById('cadastroForm');
    if (cadastroForm) {
        cadastroForm.addEventListener('submit', function(e) {
            e.preventDefault();
            alert('Solicitação de cadastro enviada com sucesso! Analisaremos suas informações e entraremos em contato em breve.');
            cadastroForm.reset();
        });
    }

    // Adiciona classe ativa ao link atual
    const currentPage = window.location.pathname.split('/').pop();
    const navLinksItems = document.querySelectorAll('.nav-links a');

    navLinksItems.forEach(link => {
        const linkHref = link.getAttribute('href');
        if (linkHref === currentPage || (currentPage === '' && linkHref === 'index.html')) {
            link.classList.add('active');
        }
    });
});
