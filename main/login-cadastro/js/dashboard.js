// Configuração da API
const API_URL = 'http://localhost:8080'; 

document.addEventListener('DOMContentLoaded', () => {
    carregarDadosUsuario();
    carregarChamados();
    carregarDizimos();

    const botao = document.getElementById('carregarDados');
    if (botao) {
        botao.addEventListener('click', carregarDadosUsuario);
    }
    
    // Configura o botão de sair
    document.querySelector('.logout a').addEventListener('click', logout);
});

// ==========================================
// 1. CARREGAR DADOS DO PERFIL (Nome, Email, etc)
// ==========================================
const usuario = 

async function carregarDadosUsuario() {
    try {
        const response = await fetch(`${API_URL}/cadastro`);
        if (!response.ok) throw new Error('Erro ao buscar dados');
        const usuario = await response.json();
        console.log(usuario);
        console.log(usuario.nome);
        // --- MODO SIMULAÇÃO (Para testar o visual agora) ---
        // --- ATUALIZANDO O HTML ---

        // 1. Atualiza Sidebar
        document.getElementById('nome-sidebar').textContent = usuario[0].nome;
        document.getElementById('avatar-iniciais').textContent = gerarIniciais(usuario[0].nome);

        // 2. Atualiza Título de Boas-vindas (Pega só o primeiro nome)
        const primeiroNome = usuario[0].nome.split(' ')[0];
        document.getElementById('titulo-boas-vindas').textContent = `Bem-vindo(a), ${primeiroNome}!`;

        // 3. Atualiza Carteirinha
        const nomeCarteirinha = document.getElementById('nome-carteirinha');
        if(nomeCarteirinha) nomeCarteirinha.textContent = usuario[0].nome;

        // 4. Preenche o Formulário de Dados Pessoais
        // Verifique se os IDs no seu HTML batem com esses abaixo
        document.getElementById('input-nome').value = usuario[0].nome;
        document.getElementById('input-email').value = usuario[0].email;

    } catch (error) {
        console.error("Erro:", error);
        // Se der erro (ex: 403 Forbidden), redireciona pro login
        // window.location.href = 'login.html';
    }
}

// ==========================================
// 2. CARREGAR HISTÓRICO DE CHAMADOS
var dados_contatos = new Map();
dados_contatos.set("Titulo", "")
dados_contatos.set("Assunto", "")
dados_contatos.set("Data", "")
dados_contatos.set("Status", "")
// ==========================================
async function carregarChamados() {
    // Simulação de dados vindos do banco
    try {
        const response = await fetch(`${API_URL}/contato`, {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dados_contato) 
        });
        if (!response.ok) throw new Error('Erro ao buscar dados');
        const usuario = await response.json();

        const chamados = [
        { protocolo: "#202601", assunto: "Dúvida sobre Batismo", data: "01/02/2026", status: "RESPONDIDO", classe: "success" },
        { protocolo: "#202605", assunto: "Alteração Cadastral", data: "03/02/2026", status: "PENDENTE", classe: "warning" }
    ];

    const tbody = document.querySelector('.styled-table tbody');
    tbody.innerHTML = ''; // Limpa tabela atual

    chamados.forEach(chamado => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${chamado.protocolo}</td>
            <td>${chamado.assunto}</td>
            <td>${chamado.data}</td>
            <td><span class="badge ${chamado.classe}">${chamado.status}</span></td>
            <td><button class="btn-icon"><i class="fas fa-eye"></i></button></td>
        `;
        tbody.appendChild(tr);
    });
    }
     catch (error) {
        console.error("Erro:", error);
        // Se der erro (ex: 403 Forbidden), redireciona pro login
        // window.location.href = 'login.html';
    }
     
}

// ==========================================
// 3. CARREGAR STATUS DO DÍZIMO (Bolinhas)
// ==========================================
function carregarDizimos() {
    // Simulação: 1 = Pago, 0 = Pendente
    // Array representa Jan, Fev, Mar, Abr...
    const statusPagamentos = [1, 1, 0, 0]; 

    const spans = document.querySelectorAll('.months-grid .month');
    
    // Zera as classes primeiro
    spans.forEach(span => span.className = 'month');

    // Aplica as classes baseado nos dados
    statusPagamentos.forEach((status, index) => {
        if (spans[index]) {
            if (status === 1) {
                spans[index].classList.add('paid'); // Verde
                spans[index].title = "Contribuição Recebida";
            } else if (status === 0 && index < new Date().getMonth() + 1) {
                // Se é mês passado e não pagou (lógica simples)
                spans[index].classList.add('pending'); // Amarelo
            }
        }
    });
}

// ==========================================
// FUNÇÕES UTILITÁRIAS
// ==========================================

// Gera as iniciais (Ex: João Silva -> JS)
function gerarIniciais(nomeCompleto) {
    const nomes = nomeCompleto.trim().split(' ');
    if (nomes.length === 1) return nomes[0].charAt(0).toUpperCase();
    return (nomes[0].charAt(0) + nomes[nomes.length - 1].charAt(0)).toUpperCase();
}

// Função de Logout
function logout(e) {
    e.preventDefault();
    if(confirm("Deseja realmente sair?")) {
        // Aqui você limparia o Token ou chamaria o endpoint de logout
        alert("Sessão encerrada.");
        window.location.href = '/main/login-cadastro/login_fiel.html';
    }
}