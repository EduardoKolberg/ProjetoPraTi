
var arrayObj=[]; // Array onde ficam salvas as informações de cadas cadastro.
var idEditar ='' // Variável que recebe o ID quando é usada a opção de editar.

function ConfirmarCadastro(){ //Caso confirme efetua o cadastro
    if (confirm("Tem certeza que deseja incluir esse Cadastro? "))
    {
        let obj = criaObjeto();
        if (confereCampos(obj.nome,obj.telefone,obj.nota, obj.data)) //chama a função para conferir se algo foi posto nos campos obrigatórios bem como os demais requisitos.
        { 
        document.getElementById("placeholder").style.display='none'; // Esconde a mensagem de que não há cadastros.
        arrayObj.push(obj);
        document.getElementById("sucessoEditar").style.display='none'; //Esconde msg de sucesso de edição caso ela esteja presente.
        document.getElementById("sucessoCadastro").style.display='block'; //Mostra msg de sucesso.
        criaTabela();
        limpar();
        }
        else {
            document.getElementById("sucessoCadastro").style.display='none'; 
        }
  
    }
}


function criaObjeto() { // Pega os dados informados no formulário e cria um objeto.
    let obj = {};
    obj.nome = document.getElementById("nome").value;
    obj.telefone = document.getElementById("telefone").value;
    obj.data = document.getElementById("data").value;
    obj.nota = document.getElementById("nota").value;
    return obj;
}

function corrigeData (objData) { // Corrige a data para aparecer dd/mm/aaaa
    if (objData == ""){
        let dataCerta = "Data não informada ou inválida."; //Quando é informada uma data inválida no input date ele passa vazio, por exemplo se botar dia 31 de abril ele passa como vazio, por isso essa msg.
        return dataCerta;
    }
    let dataArray = objData.split("-");
    let dataCerta = dataArray[2] + "/" + dataArray[1] + "/" + dataArray[0];
    return dataCerta;
}

function criaTabela() { 
    let destino = document.getElementById("destino");
    let html = "";
    let titulo ="<tr><th colspan=12><h2>Alunos Cadastrados</h2></th></tr>";
    let colunas = "<tr><th colspan=2>Nome</th><th colspan=2>Telefone</th><th colspan=2 >Data de Nascimento</th><th colspan=2 >Nota final</th> <th colspan=2 >Opções</th></tr>";
    for(i=0;i<=arrayObj.length-1;i++){ 
        
        let data = corrigeData(arrayObj[i].data);
        let cadastro = '<tr id="L"'+i+'"><td colspan=2>' + arrayObj[i].nome+'</td><td colspan=2>'+arrayObj[i].telefone+ '</td><td colspan=2>'+ data +'</td><td colspan=2>'+arrayObj[i].nota+'</td> <td><a id="excluir" onclick="excluiCadastro(\'L'+ i +'\');">Excluir</a> / <a id="editar" onclick="editar(\'L'+ i +'\');">Editar</a> </td> </tr>';

        html += cadastro;
    }
    destino.innerHTML =titulo + colunas + html;
}

function limpar() {   // Limpa os campos após o cadastro e edição de dados, facilitando novo cadastro.
    document.getElementById("nome").value = "";
    document.getElementById("telefone").value = "";
    document.getElementById("data").value = "";
    document.getElementById("nota").value = "";
 }


function confereCampos(nome,telefone,nota,data){
    let dataAtual = new Date(); //Pega a data atual;
    let mesAtual = dataAtual.getMonth();
    let anoAtual = dataAtual.getFullYear();
    let diaAtual = dataAtual.getDate();


    data= new Date(data.replace(/-/g, '\/'));
    let mes = data.getMonth();
    let ano = data.getFullYear();
    let dia = data.getDate();

    if (nome =='' || telefone == ''){
        alert("Favor preencher todos os campos obrigatórios");
        return false;
    }
    else if (!(/[a-zA-Z]/.test(nome))) { //Confere se o nome tem pelo menos alguma letra.
        alert("Favor inserir um nome válido");
        document.getElementById("nome").value ='';
        return false;
    }
    else if (nota >10 || nota < 0){
        alert("Favor inserir uma nota válida (0-10)");
        document.getElementById("nota").value ='';
        return false;
    }
    else if (ano > anoAtual) { // Impede que se use data futura como data de nascimento.
        alert("Favor inserir um ano válido");
        return false;
    }
    else if (ano == anoAtual){
        if (mes > mesAtual){
            alert("Favor inserir um mês válido");
            return false;
        }
        else if (mes == mesAtual && dia > diaAtual){
            alert("Favor inserir um dia válido");
            return false; 
        }
        else {
            return true;
         }
    }
    else {
        return true;
    }
}


 function pegaID(idAluno){
    let ID =''
        for (i=1; i<idAluno.length;i++){ //garante que a exclusão e edição funcione independente do numero de cadastros, isolando o numero ID que corresponde a posição no array para excluir.
            ID+=idAluno[i]
        }
        return ID;
}

function excluiCadastro(idAluno){
    if (confirm("Tem certeza que deseja excluir essa entrada? "))
    {
        let ID = pegaID(idAluno);
        arrayObj.splice(ID,1);
        criaTabela();
    }
}

function mostrarBotoes(){
    document.getElementById("botaoSalvar").type = "button";
    document.getElementById("cancelar").type = "button";
    document.getElementById("botaoCadastro").type = "hidden";
}

function esconderBotoes(){
    document.getElementById("botaoSalvar").type = "hidden";
    document.getElementById("cancelar").type = "hidden";
    document.getElementById("botaoCadastro").type = "button";
}

 function editar(idAluno) { //Pega o ID e bota as informações correspondentes nos campos para serem modificadas.
    idEditar=''; 
    mostrarBotoes();
    idEditar = pegaID(idAluno);
    document.getElementById("nome").value = arrayObj[idEditar].nome;
    document.getElementById("telefone").value = arrayObj[idEditar].telefone;
    document.getElementById("data").value = arrayObj[idEditar].data;
    document.getElementById("nota").value = arrayObj[idEditar].nota;
 }

 function salvarAlteracoes() {
    if (confirm("Tem certeza que deseja modificar esse Cadastro? "))
    {
        arrayObj[idEditar]= criaObjeto();
        if (confereCampos(arrayObj[idEditar].nome,arrayObj[idEditar].telefone,arrayObj[idEditar].nota,arrayObj[idEditar].data)){ 
            document.getElementById("sucessoCadastro").style.display='none'; //Esconde msg de sucesso de cadastro
            document.getElementById("sucessoEditar").style.display='block'; //Mostra msg de sucesso da edição.
            criaTabela();
            limpar();
            esconderBotoes();
            idEditar ='';
        }
        else {
            document.getElementById("sucessoEditar").style.display='none'; 
        }
    }
 }

 function cancelar() {
    limpar();
    esconderBotoes();
    idEditar ='';
 }
