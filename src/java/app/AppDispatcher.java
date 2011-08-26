package app;

import core.Env;
import app.controller.*;
import java.io.PrintWriter;

/**
 * Classe Utilizada para determinar qual controller sera chamado
 *
 * @group MyLastJavaApp
 */
public class AppDispatcher {

    Env env;
    PrintWriter out;

    public boolean dispatch(Env env, PrintWriter out)
    {
        this.env = env;
        this.out = out;

        if (env.controller.equals("index")) {
            return this.dispatchIndex();
        } else if(env.controller.equals("produto")) {
             return this.dispatchProduto();
        } else if(env.controller.equals("funcionario")) {
             return this.dispatchFuncionario();
        } else if(env.controller.equals("cliente")) {
             return this.dispatchCliente();
        } else if(env.controller.equals("fornecedor")) {
             return this.dispatchFornecedor();
        } else if(env.controller.equals("login")) {
             return this.dispatchLogin();
        } else if(env.controller.equals("venda")) {
             return this.dispatchVenda();
        } else if(env.controller.equals("entrada")) {
             return this.dispatchEntrada();
        } else if(env.controller.equals("item-entrada")) {
             return this.dispatchItemEntrada();
        } else if(env.controller.equals("item-venda")) {
             return this.dispatchItemVenda();
        } else if(env.controller.equals("relatorio")) {
             return this.dispatchRelatorio();
        } else {
            out.println("Controller não encontrado: " + env.controller);
            return false;
        }
    }

    private boolean dispatchIndex()
    {
        IndexController c = new IndexController();
        c._env = this.env;
        if (env.action.equals("index")) {
            c.index();
        } else {
            out.println("Action não encontrada: " + env.action);
            return false;
        }
        return true;
    }

    private boolean dispatchProduto()
    {
        ProdutoController p = new ProdutoController();
        p._env = env;
        if (env.action.equals("index")) {
            p.index();
        } else if (env.action.equals("adicionar")) {
            p.adicionar();
        } else if (env.action.equals("editar")) {
            p.editar();
        } else if (env.action.equals("excluir"))  {
            p.excluir();
        } else {
            out.println("Action não encontrada: " + env.action);
            return false;
        }
        return true;
    }

    private boolean dispatchFuncionario()
    {
        FuncionarioController f = new FuncionarioController();
        f._env = env;
        if (env.action.equals("index")) {
            f.index();
        } else if (env.action.equals("adicionar")) {
            f.adicionar();
        } else if (env.action.equals("editar")) {
            f.editar();
        } else if (env.action.equals("excluir")) {
            f.excluir();
        } else {
            out.println("Action não encontrada: " + env.action);
            return false;
        }
        return true;
    }


    private boolean dispatchCliente()
    {
        ClienteController c = new ClienteController();
        c._env = env;
        if (env.action.equals("index")) {
            c.index();
        } else if (env.action.equals("adicionar")) {
            c.adicionar();
        } else if (env.action.equals("editar")) {
            c.editar();
        } else if (env.action.equals("excluir")) {
            c.excluir();
        } else {
            out.println("Action não encontrada: " + env.action);
            return false;
        }
        return true;
    }

    private boolean dispatchFornecedor()
    {
        FornecedorController f = new FornecedorController();
        f._env = env;
        if (env.action.equals("index")) {
            f.index();
        } else if (env.action.equals("adicionar")) {
            f.adicionar();
        } else if (env.action.equals("editar")) {
            f.editar();
        } else if (env.action.equals("excluir")) {
           f.excluir();
        } else {
            out.println("Action não encontrada: " + env.action);
            return false;
        }
        return true;
    }

    private boolean dispatchLogin()
    {
        LoginController l = new LoginController();
        l._env = env;
        if (env.action.equals("index")) {
            l.index();
        } else if (env.action.equals("sair")) {
            l.sair();
        } else {
            out.println("Action não encontrada: " + env.action);
            return false;
        }
        return true;
    }

    private boolean dispatchVenda()
    {
        VendaController v = new VendaController();
        v._env = env;
        if (env.action.equals("index")) {
            v.index();
        }else if (env.action.equals("adicionar")) {
            v.adicionar();
        }else if (env.action.equals("detalhes")) {
            v.detalhes();
        }else if (env.action.equals("cancelar")) {
            v.cancelar();
        }else if (env.action.equals("fechar")) {
            v.fechar();
        }else {
            out.println("Action não encontrada: " + env.action);
            return false;
        }
        return true;
    }

    private boolean dispatchEntrada()
    {
        EntradaController e = new EntradaController();
        e._env = env;
        if (env.action.equals("index")) {
            e.index();
        }else if (env.action.equals("adicionar")) {
            e.adicionar();
        }else if (env.action.equals("detalhes")) {
            e.detalhes();
        }else if (env.action.equals("fechar")) {
            e.fechar();
        }else if (env.action.equals("cancelar")) {
            e.cancelar();
        }else {
            out.println("Action não encontrada: " + env.action);
            return false;
        }
        return true;
    }

    private boolean dispatchItemEntrada()
    {
        ItemEntradaController i = new ItemEntradaController();
        i._env = env;
        if (env.action.equals("excluir")) {
            i.excluir();
        }else{
            out.println("Action não encontrada: " + env.action);
            return false;
        }
        return true;
    }

    private boolean dispatchItemVenda()
    {
        ItemVendaController i = new ItemVendaController();
        i._env = env;
        if (env.action.equals("excluir")) {
            i.excluir();
        }else{
            out.println("Action não encontrada: " + env.action);
            return false;
        }
        return true;
    }
    
    private boolean dispatchRelatorio()
    {
        RelatorioController i = new RelatorioController();
        i._env = env;
        if (env.action.equals("index")) {
            i.index();
        } else
        if (env.action.equals("produto")) {
            i.produto();
        }else if (env.action.equals("notafiscal")) {
            i.notafiscal();
        }else{
            out.println("Action não encontrada: " + env.action);
            return false;
        }
        return true;
    }

}