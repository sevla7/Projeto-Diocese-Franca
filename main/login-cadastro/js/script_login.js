const LOGIN_URL = 'http://localhost:8080/auth/login'; 

async function logar(dados) {
    try {
        const response = await fetch(LOGIN_URL, { 
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dados) 
        });
        
        return response; 
        
    } catch (error) {
        console.error('Falha na comunicação de rede:', error);
        throw new Error('Não foi possível conectar ao servidor. Verifique se a API está rodando.');
    }
}

document.querySelector("form").addEventListener('submit', function(event) {
    event.preventDefault();

    // 1. Coleta dos dados do formulário de login
    const dadosLogin = {
        email: document.getElementById('email-login').value, // Assumindo ID 'email-login' no seu input
        senha: document.getElementById('senha-login').value  // Assumindo ID 'senha-login' no seu input
    };
    
    // 2. Chama a função de login
    logar(dadosLogin)
        .then(response => {
           if (response.ok) {
            return response.json().then(usuario => {
            // ✅ SALVA NO LOCALSTORAGE
            localStorage.setItem('usuarioId', usuario.id);
            localStorage.setItem('email', usuario.email);
            localStorage.setItem('nome', usuario.nome);

            alert('Login efetuado com sucesso!');
            window.location.href = 'fiel_dashboard.html';
            });


            } else if (response.status === 401) {
                alert('Erro: E-mail ou senha inválidos.');
            } 
            else {
                // Trata outros erros do servidor (ex: 500)
                return response.json().then(err => { throw new Error(err.message || 'Erro desconhecido.') });
            }
        })
        .catch(error => {
            alert(`Erro: ${error.message}`);
        });
});

