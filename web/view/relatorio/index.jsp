<%@page import="core.Link" %>
<h2>Relatórios</h2>
<div class="col-esq">
    <ul id="tabs" class="menu-relatorio">
        <li><a href="#produto">Produtos</a></li>
        <li><a href="#notafiscal">Emitir Nota Fiscal</a></li>
    </ul>
</div>
<div class="col-dir">

    <!-- Inicio Produtos -->
    <fieldset id="produto" class="painel">
        <legend>Produtos</legend>
        <form id="form-produto" target="_self" method="post" action="<% out.print(Link.criar("relatorio/produto")); %>">
            
            
                <label>
                    <input type="radio" name="tipo" value="pdf"/>PDF
                </label>
                <label>
                    <input type="radio" name="tipo" value="print" checked/> Imprimir
                </label>

            <p class="form-botao">
                <input type="submit" name="bt_print" id="bt_print" value="Gerar Relatório" class="botao">
            </p>
        </form>
    </fieldset>
    <!-- Fim Produtos -->

    <!-- Inicio Nota -->
    <fieldset id="notafiscal" class="painel">
        <legend>Nota Fiscal</legend>
        <form id="form-notafiscal" target="_self" method="post" action="<% out.print(Link.criar("relatorio/notafiscal")); %>?origem=relatorio">


                <label>
                    Código Venda:
                    <input type="input" name="codigo" value=""/>
                </label>
                <br clear="all"/>
                <label>
                    <input type="radio" name="tipo" value="pdf" /> PDF
                </label>
                <label>
                    <input type="radio" name="tipo" value="print" checked/> Imprimir
                </label>

            <p class="form-botao">
                <input type="submit" name="bt_print" id="bt_print" value="Gerar Relatório" class="botao">
            </p>
        </form>
    </fieldset>
    <!-- Fim nota -->

</div>
<script type="text/javascript">
// QUANDO O DOCUMENTO ESTIVER PRONTO
$(document).ready(function(){
    $('a').click(function(){
        $('fieldset.painel').hide();
        $('#tabs>li>a.ativa').removeClass('ativa');
        var ancora = $(this).attr('href');
        $(this).addClass('ativa')
        $(ancora).slideDown('slow');
    });
});
$('#produto').show();
$('#produto').addClass('ativa')
</script>