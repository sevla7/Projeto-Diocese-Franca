 document.querySelector("form").addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o envio padrão do formulário (que recarrega a página)
    
    
    // 1. Coleta os dados do formulário
    const dados = {
        nome: document.getElementById('nome').value,
        email: document.getElementById('email').value,
        titulo: document.getElementById('titulo').value,
        categoria: Number(document.getElementById('categoria').value),
        mensagem: document.getElementById('mensagem').value
    };

    // 2. Requisição HTTP POST para o seu Endpoint Java
    fetch('http://localhost:8080/contato', {
        method: 'POST', // Define o método HTTP para Criação
        headers: {
            'Content-Type': 'application/json', // Informa ao Java que os dados são JSON
        },
        body: JSON.stringify(dados), // Converte os dados do JavaScript em JSON
    })
    .then(response => {
        if (response.ok) {
            alert('Chamado enviado com sucesso! A equipe responderá em breve.');
            // Limpa o formulário ou redireciona
        } else {
            alert('Erro ao enviar o chamado. Tente novamente.');
        }
    })
    .catch(error => {
        console.error('Erro de conexão:', error);
        alert('Falha na comunicação com o servidor.');
    });
});