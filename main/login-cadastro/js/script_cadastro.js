    // A URL deve ser definida de forma estável para evitar erros de "Invalid name"
    const CADASTRO_URL = 'http://localhost:8080/cadastro'; 

    async function cadastrar(dados) {
        // Linha 10 (onde a requisição fetch é tipicamente chamada)
        try {
            const response = await fetch(CADASTRO_URL, { 
                method: 'POST', 
                
                // ⚠️ Verifique rigorosamente esta seção para garantir que não há caracteres inválidos ou variáveis indefinidas
                headers: {
                    'Content-Type': 'application/json'
                },
                
                body: JSON.stringify(dados) 
            });
            
            return response; 
            
        } catch (error) {
            // Erro de rede ou servidor
            console.error('Falha na comunicação:', error);
            throw new Error('Não foi possível conectar ao servidor Java. Verifique se a API está rodando.');
        }
    }

    document.querySelector("form").addEventListener('submit', function(event) {
        event.preventDefault();

        // Exemplo de como pegar os dados dos campos (assumindo IDs)
        const dadosFiel = {
            nome: document.getElementById('nome').value,
            email: document.getElementById('email').value,
            paroquia: document.getElementById('paroquia').value,
            senha: document.getElementById('senha').value
            // ... outros campos
        };
        
        // Chama a função e trata a Promessa
        cadastrar(dadosFiel)
            .then(response => {
                // Lógica de tratamento de sucesso (200, 201) ou erro do servidor (409, 400)
                if (response.ok) {
                    alert('Cadastro realizado com sucesso! Faça login.');
                    window.location.href = 'fiel_dashboard.html';
                } else {
                    return response.json().then(err => { 
                        throw new Error(err.message || 'Erro ao cadastrar Fiel.'); 
                    });
                }
            })
            .catch(error => {
                alert(`Erro: ${error.message}`);
            });
    });

