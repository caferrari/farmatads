$(document).ready(function(){
	$('.buttons a, form p.submit input, p.submit a.submit, form input[type=submit]').button();
	$("a[title]").tipTip({maxWidth: "auto", edgeOffset: 10});


    var msg = $("#mensagem");

    msg.slideDown("normal", function(){

    if (msg.length){
            msg.find(".fechar").click(function(){
                $(this).parent().slideUp("fast", function(){ $(this).remove(); });
                return false;
            });
            msg.click(function() { msg.find('.fechar').trigger('click'); });
            setTimeout(function(){ msg.find('.fechar').trigger('click'); }, 5000);
        }

    })

    $('.goback').click(function(){ history.go(-1); return false; });

    b = $('body');
    $('.menu a').each(function(){
        var c = $(this).attr('class');
        if (b.hasClass('c_' + c)){
             $(this).addClass('active');
            return false;
        }
    });

    $(".confirm").click(function(){
        var html = $("<div><p>Confirmar ação?</p></div>");
        var link = $(this).attr("href");

        html.dialog({
                title: 'Confirmar Ação',
                resizable: false,
                height:140,
                modal: true,
                buttons: {
                    'Cancelar': function() {
                        $(this).dialog('close');
                    },
                    'Confirmar': function() {
                        top.location = link;
                        $(this).dialog('close');
                    }
                }
            });
        return false;
    })

});
