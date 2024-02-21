package Cadastro.NovosDados.Areas;

import Cadastro.Database.Metodos.metodosFuncionarios;
import Cadastro.Database.dataSet;
import Cadastro.NovosDados.Repositorio.DTO.Funcionarios;
import Raiz.Acesso.menuPrincipal;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.leitorDados;
import Raiz.Utils.smartTools.*;

public class areaCadastroFuncionario extends leitorDados implements IAreaCadastro {

    menuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesFuncionarios an;
    dataSet<?> banco;

    public areaCadastroFuncionario(dataSet<?> DS) {
        this.mp = new menuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.an = cad.new AcoesFuncionarios();
        this.banco = DS;
    }

    @Override
    public void menuAreaCadastro(Integer userId) {
        System.out.println("\n\u001B[34mFuncionário:\u001B[0m");
        System.out.println("1 - Cadastrar Funcionário");
        System.out.println("2 - Alterar Funcionário");
        System.out.println("3 - Excluir Funcionário");
        System.out.println("4 - Localizar Funcionário");
        System.out.println("5 - Localizar Mais Funcionários");
        System.out.println("6 - Remover Mais Funcionários");
        System.out.println("7 - Listar Funcionários");
        System.out.println("* - Mais");

        System.out.print("\n-----------------------------------------");
        String id = readText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        acoesAreaCadastro(id, userId);
    }

    boolean session = true;
    @Override
    public void acoesAreaCadastro(String id, Integer userId) {
        while (session) {
            switch (id) {
                case "1":
                    //#region Cadastrar novo funcionário
                    System.out.println("\n# Cadastrar novo funcionário #\n");
                    //an.cadastrarFuncionario("Jorge", 22, "Homem", "04472205484", "teste@olos.com.br", "014585445489", "04472205", 38, "RH", "Contratos");
                    int funcId = an.cadastrar(
                            readSentence("Nome: "),
                            readInt("Idade: "),
                            readText("Gênero: "),
                            readMask("CPF: "),
                            readText("E-mail: "),
                            readSentence("Telefone: "),
                            readText("CEP: ").replace("-",""),
                            readInt("Nùmero da residência: "),
                            readSentence("Area: "),
                            readSentence("Departamento: ")
                    );
                    if (!(metodosFuncionarios.message == null))
                        System.out.println(metodosFuncionarios.message);
                    else System.out.println("\nCadastro concluído!");

                    String newAction = readSentence("Deseja criar um usuário para esse funcionário? (S/N): ").toUpperCase().substring(0, 1);                                        
                    while (true) {
                        if (newAction.equals("S")) {
                            areaCadastroUsuario acu = new areaCadastroUsuario(banco, an.retFuncionarios(funcId));
                            acu.acoesAreaCadastro("1", userId);
                            break;
                        }
                        if (newAction.equals("N")) opcoesAreaCadastro(Funcionarios.class.getName(), userId);
                        //if (!newAction.equals("S") || !newAction.equals("N")) {
                        System.out.println("Opção inválida!");
                        newAction = readSentence("Deseja criar um usuário para esse funcionário? (S/N): ").toUpperCase().substring(0, 1);
                        //}
                    }
                    break;
                    //#endregion
                case "2":
                    //#region Alterar um funcionário
                    System.out.println("\n# Alterar um funcionário #\n");
                    if(an.listar()){
                        System.out.println();

                        int alterId = readInt("Id: ");
                        if (an.validarId(alterId)) {
                            String field = readText("Campo: ");
                            an.alterar(
                                    alterId,
                                    field,
                                    readSentence("Alteração (" + field.toUpperCase() + "): ")
                            );
                            if (!(metodosFuncionarios.message == null))
                                System.out.println(metodosFuncionarios.message);
                            else System.out.println("\nAlteração concluída!");
                            an.localizar(alterId);
                        }
                        if (!(metodosFuncionarios.message == null))
                            System.out.println(metodosFuncionarios.message);
                    }

                    opcoesAreaCadastro(Funcionarios.class.getName(), userId);
                    break;
                    //#endregion
                case "3":
                    //#region Excluir um funcionário
                    System.out.println("\n# Excluir um funcionário #\n");
                    if(an.listar()){
                        System.out.println();

                        int remoId = readInt("Id: ");
                        if (an.validarId(remoId)) {
                            an.excluir(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(metodosFuncionarios.message == null))
                                System.out.println(metodosFuncionarios.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(metodosFuncionarios.message == null))
                            System.out.println(metodosFuncionarios.message);
                    }

                    opcoesAreaCadastro(Funcionarios.class.getName(), userId);
                    break;
                    //#endregion
                case "4":
                    //#region Localizar um funcionário
                    System.out.println("\n# Localizar um funcionário #\n");
                    if(an.listar()){
                        System.out.println();

                        int findId = readInt("Id: ");
                        if (an.validarId(findId)) {
                            an.localizar(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(metodosFuncionarios.message == null))
                                System.out.println(metodosFuncionarios.message);
                        }
                        if (!(metodosFuncionarios.message == null))
                            System.out.println(metodosFuncionarios.message);
                    }

                    opcoesAreaCadastro(Funcionarios.class.getName(), userId);
                    break;
                    //#endregion
                case "5":
                    //#region Localizar vários funcionários
                    System.out.println("\n# Localizar vários usuários #\n");
                    if(an.listar()){
                        System.out.println();

                        int findId = readInt("Início: ");
                        if (an.validarId(findId)) {
                            an.localizarMais(
                                    findId, //ReadInt("Início: "),
                                    readInt("Fim: "));
                            if (!(metodosFuncionarios.message == null))
                                System.out.println(metodosFuncionarios.message);
                        }
                        if (!(metodosFuncionarios.message == null))
                            System.out.println(metodosFuncionarios.message);
                    }

                    opcoesAreaCadastro(Funcionarios.class.getName(), userId);
                    break;
                    //#endregion
                case "6":
                    //#region Remover vários funcionários
                    System.out.println("\n# Remover vários funcionários #\n");
                    if(an.listar()){
                        System.out.println();

                        int remoId = readInt("Início: ");
                        if (an.validarId(remoId)) {
                            an.removerMais(
                                    remoId, //ReadInt("Início: "),
                                    readInt("Fim: ")
                            );
                            if (!(metodosFuncionarios.message == null))
                                System.out.println(metodosFuncionarios.message);
                        }
                        if (!(metodosFuncionarios.message == null))
                            System.out.println(metodosFuncionarios.message);
                    }

                    opcoesAreaCadastro(Funcionarios.class.getName(), userId);
                    break;
                    //#endregion
                case "7":
                    //#region Lista de funcionários
                    System.out.println("\n# Lista de funcionários #\n");
                    an.listar();

                    opcoesAreaCadastro(Funcionarios.class.getName(), userId);
                    break;
                    //#endregion
                case "*":
                    //#region Retorno ao menu
                    opcoesAreaCadastro(Funcionarios.class.getName(), userId);
                    break;
                    //#endregion
                default:
                    break;
            }
        }
    }

    @Override
    public void opcoesAreaCadastro(String classe, Integer userId) {
        objetosAuxiliares obj = new objetosAuxiliares();
        System.out.print("\n----------------------------------------------");
        Integer opcao = obj.optionMenu(classe);
        System.out.println("----------------------------------------------");

        if (opcao == 1) menuAreaCadastro(userId);
        if (opcao == 2) {
            session = false;
            System.out.print("\033[2J\033[1;1H");
            mp.paginaInicial(banco);
        }
        if (opcao == 3) {
            session = false;
            System.out.print("\033[2J\033[1;1H");
            mp.menuCadastro(userId, banco);
        }
        if (opcao == 4) {
            System.out.println("\nAplicação encerrada.");
            System.exit(0);
        }
    }
}
