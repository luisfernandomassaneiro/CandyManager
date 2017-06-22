package br.senai.sc.tcc.candymanager.controller;

import android.provider.BaseColumns;

/**
 * Created by luis.massaneiro on 20/06/2017.
 */

public class MetadadosHelper implements BaseColumns {
    public static final String ATIVO = "ATIVO";

    private MetadadosHelper(){}

    public static class TabelaPessoa implements BaseColumns {
        public static String TB_PESSOA = "TB_PESSOA";
        public static String PES_NOME = "PES_NOME";
        public static String PES_TELEFONE = "PES_TELEFONE";
        public static String PES_EMAIL = "PES_EMAIL";

        public static String getCreateEntry(){
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE ").append(TB_PESSOA).append(" ( ");
            sb.append(TabelaPessoa._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sb.append(PES_NOME).append(" VARCHAR(50) NOT NULL, ");
            sb.append(PES_TELEFONE).append(" VARCHAR(20), ");
            sb.append(ATIVO).append(" INT ");
            /*sb.append(", FOREIGN KEY (").append(COLUMN_LINHA).append(") REFERENCES ").append(TLinhas.TABLE_NAME).append("(")
                    .append(TLinhas._ID).append(") ON DELETE CASCADE");*/
            sb.append(")");
            return sb.toString();
        }

        public static String getDropTable() {
            return "DROP TABLE IF EXISTS " + TB_PESSOA;
        }
    }

    public static class TabelaProduto implements BaseColumns {
        public static String TB_PRODUTO = "TB_PRODUTO";
        public static String PRO_CODIGO = "PRO_CODIGO";
        public static String PRO_DESCRICAO = "PRO_DESCRICAO";
        public static String PRO_QTDEATUAL = "PRO_QTDEATUAL";
        public static String PRO_VALORCOMPRA = "PRO_VALORCOMPRA";
        public static String PRO_VALORVENDA = "PRO_VALORVENDA";

        public static String getCreateEntry(){
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE ").append(TB_PRODUTO).append(" ( ");
            sb.append(TabelaProduto._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sb.append(PRO_DESCRICAO).append(" VARCHAR(50) NOT NULL, ");
            sb.append(PRO_CODIGO).append(" VARCHAR(20), ");
            sb.append(PRO_QTDEATUAL).append(" INTEGER, ");
            sb.append(PRO_VALORCOMPRA).append(" NUMERIC(18,2), ");
            sb.append(PRO_VALORVENDA).append(" NUMERIC(18,2), ");
            sb.append(ATIVO).append(" INT ");
            /*sb.append(", FOREIGN KEY (").append(COLUMN_LINHA).append(") REFERENCES ").append(TLinhas.TABLE_NAME).append("(")
                    .append(TLinhas._ID).append(") ON DELETE CASCADE");*/
            sb.append(")");
            return sb.toString();
        }

        public static String getColunasParaSelect() {
            StringBuilder sb = new StringBuilder();
            sb.append(PRO_CODIGO).append(", ").append(PRO_DESCRICAO).append(", ").append(PRO_QTDEATUAL).append(", ").append(PRO_VALORCOMPRA).append(", ").append(PRO_VALORVENDA);
            return sb.toString();
        }

        public static String getDropTable() {
            return "DROP TABLE IF EXISTS " + TB_PRODUTO;
        }
    }
    public static class TabelaMovimentoEstoque implements BaseColumns {
        public static String TB_MOVIMENTO_ESTOQUE = "TB_MOVIMENTO_ESTOQUE";
        public static String MOV_DATA = "MOV_DATA";
        public static String MOV_QTNDE = "MOV_QTNDE";
        public static String MOV_TIPO = "MOV_TIPO";
        public static String MOV_PROID = "MOV_PROID";

        public static String getCreateEntry(){
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE ").append(TB_MOVIMENTO_ESTOQUE).append(" ( ");
            sb.append(TabelaMovimentoEstoque._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sb.append(MOV_DATA).append(" TIMESTAMP NOT NULL, ");
            sb.append(MOV_QTNDE).append(" INTEGER NOT NULL, ");
            sb.append(MOV_TIPO).append(" VARCHAR(10) NOT NULL, ");
            sb.append(MOV_PROID).append(" INTEGER NOT NULL ");
            sb.append(", FOREIGN KEY (").append(MOV_PROID).append(") REFERENCES ").append(TabelaProduto.TB_PRODUTO).append("(")
                    .append(TabelaProduto._ID).append(") ON DELETE CASCADE");
            sb.append(")");
            return sb.toString();
        }

        public static String getDropTable() {
            return "DROP TABLE IF EXISTS " + TB_MOVIMENTO_ESTOQUE;
        }
    }

    public static class TabelaPedido implements BaseColumns {
        public static String TB_PEDIDO = "TB_PEDIDO";
        public static String PED_DATA = "PED_DATA";
        public static String PED_VALORPAGO = "PED_VALORPAGO";
        public static String PED_VALORTOTAL = "PED_VALORTOTAL";
        public static String PED_FINALIZADO = "PED_FINALIZADO";
        public static String PED_CLIID = "PED_CLIID";

        public static String getCreateEntry(){
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE ").append(TB_PEDIDO).append(" ( ");
            sb.append(TabelaPedido._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sb.append(PED_DATA).append(" TIMESTAMP NOT NULL, ");
            sb.append(PED_VALORPAGO).append(" NUMERIC(18,2), ");
            sb.append(PED_VALORTOTAL).append(" NUMERIC(18,2), ");
            sb.append(PED_FINALIZADO).append(" INT, ");
            sb.append(PED_CLIID).append(" INTEGER NOT NULL");
            sb.append(", FOREIGN KEY (").append(PED_CLIID).append(") REFERENCES ").append(TabelaPessoa.TB_PESSOA).append("(")
                    .append(TabelaPessoa._ID).append(" ) ");
            sb.append(")");
            return sb.toString();
        }

        public static String getDropTable() {
            return "DROP TABLE IF EXISTS " + TB_PEDIDO;
        }
    }

    public static class TabelaPedidoItem implements BaseColumns {
        public static String TB_PEDIDO_ITEM = "TB_PEDIDO_ITEM";
        public static String PIT_QNTDE = "PIT_QNTDE";
        public static String PIT_VALORCOMPRA = "PIT_VALORCOMPRA";
        public static String PIT_VALORVENDA = "PIT_VALORVENDA";
        public static String PIT_PEDID = "PIT_PEDID";
        public static String PIT_PROID = "PIT_PROID";

        public static String getCreateEntry(){
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE ").append(TB_PEDIDO_ITEM).append(" ( ");
            sb.append(TabelaPedidoItem._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sb.append(PIT_QNTDE).append(" INTEGER NOT NULL, ");
            sb.append(PIT_VALORCOMPRA).append(" NUMERIC(18,2), ");
            sb.append(PIT_VALORVENDA).append(" NUMERIC(18,2), ");
            sb.append(PIT_PEDID).append(" INTEGER NOT NULL, ");
            sb.append(PIT_PROID).append(" INTEGER NOT NULL");
            sb.append(", FOREIGN KEY (").append(PIT_PEDID).append(") REFERENCES ").append(TabelaPedido.TB_PEDIDO).append("(")
                    .append(TabelaPedido._ID).append(" ), ");
            sb.append(", FOREIGN KEY (").append(PIT_PROID).append(") REFERENCES ").append(TabelaProduto.TB_PRODUTO).append("(")
                    .append(TabelaProduto._ID).append(" ) ");
            sb.append(")");
            return sb.toString();
        }

        public static String getDropTable() {
            return "DROP TABLE IF EXISTS " + TB_PEDIDO_ITEM;
        }
    }

    public static String getCreatesTables() {
        StringBuilder sb = new StringBuilder();
        sb.append(TabelaPessoa.getCreateEntry()).append("; ");
        sb.append(TabelaProduto.getCreateEntry()).append("; ");
        sb.append(TabelaMovimentoEstoque.getCreateEntry()).append("; ");
        sb.append(TabelaPedido.getCreateEntry()).append("; ");
        sb.append(TabelaPedidoItem.getCreateEntry()).append("; ");
        return sb.toString();
    }

    public static String getDropTables() {
        StringBuilder sb = new StringBuilder();
        sb.append(TabelaPessoa.getDropTable()).append("; ");
        sb.append(TabelaProduto.getDropTable()).append("; ");
        sb.append(TabelaMovimentoEstoque.getDropTable()).append("; ");
        sb.append(TabelaPedido.getDropTable()).append("; ");
        sb.append(TabelaPedidoItem.getDropTable()).append("; ");
        return sb.toString();
    }
}