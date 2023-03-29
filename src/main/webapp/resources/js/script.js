;(function($) {
    "use strict";  
    
    //* Form js
    function verificationForm(){
        //jQuery time
        var current_fs, next_fs, previous_fs; //fieldsets
        var left, opacity, scale; //fieldset properties which we will animate
        var animating; //flag to prevent quick multi-click glitches

        $(".next").click(function () {
            if (animating) return false;
            animating = true;

            current_fs = $(this).parent();
            next_fs = $(this).parent().next();

            //activate next step on progressbar using the index of next_fs
            $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

            //show the next fieldset
            next_fs.show();
            //hide the current fieldset with style
            current_fs.animate({
                opacity: 0
            }, {
                step: function (now, mx) {
                    //as the opacity of current_fs reduces to 0 - stored in "now"
                    //1. scale current_fs down to 80%
                    scale = 1 - (1 - now) * 0.2;
                    //2. bring next_fs from the right(50%)
                    left = (now * 50) + "%";
                    //3. increase opacity of next_fs to 1 as it moves in
                    opacity = 1 - now;
                    current_fs.css({
                        'transform': 'scale(' + scale + ')',
                        'position': 'absolute'
                    });
                    next_fs.css({
                        'left': left,
                        'opacity': opacity
                    });
                },
                duration: 800,
                complete: function () {
                    current_fs.hide();
                    animating = false;
                },
                //this comes from the custom easing plugin
                easing: 'easeInOutBack'
            });
        });

        $(".previous").click(function () {
            if (animating) return false;
            animating = true;

            current_fs = $(this).parent();
            previous_fs = $(this).parent().prev();

            //de-activate current step on progressbar
            $("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

            //show the previous fieldset
            previous_fs.show();
            //hide the current fieldset with style
            current_fs.animate({
                opacity: 0
            }, {
                step: function (now, mx) {
                    //as the opacity of current_fs reduces to 0 - stored in "now"
                    //1. scale previous_fs from 80% to 100%
                    scale = 0.8 + (1 - now) * 0.2;
                    //2. take current_fs to the right(50%) - from 0%
                    left = ((1 - now) * 50) + "%";
                    //3. increase opacity of previous_fs to 1 as it moves in
                    opacity = 1 - now;
                    current_fs.css({
                        'left': left
                    });
                    previous_fs.css({
                        'transform': 'scale(' + scale + ')',
                        'opacity': opacity
                    });
                },
                duration: 800,
                complete: function () {
                    current_fs.hide();
                    animating = false;
                },
                //this comes from the custom easing plugin
                easing: 'easeInOutBack'
            });
        });

        $(".submit").click(function () {
            return false;
        })
    }; 
    
    //* Add Phone no select
    function phoneNoselect(){
        if ( $('#msform').length ){   
            $("#phone").intlTelInput(); 
            $("#phone").intlTelInput("setNumber", "+880"); 
        };
    }; 
    //* Select js
    function nice_Select(){
        if ( $('.product_select').length ){ 
            $('select').niceSelect();
        };
    }; 
    /*Function Calls*/  
    verificationForm ();
    phoneNoselect ();
    nice_Select ();
})(jQuery);
document.getElementById("region").onchange = function () {
    filtre_array(document.getElementById("ecole").value,document.getElementById("region").value,document.getElementById("district").value);
}   
document.getElementById("ecole").onkeyup = function () {
    filtre_array(document.getElementById("ecole").value,document.getElementById("region").value,document.getElementById("district").value);
}
document.getElementById("district").onchange = function () {
    filtre_array(document.getElementById("ecole").value,document.getElementById("region").value,document.getElementById("district").value);
}
document.getElementById("idfiliere").onchange = function () {
    if(this.value==1){
        document.getElementById("inputa").innerHTML="<br><div class=form-group><input type=text class=form-control name=note_math placeholder=\"Note Mathematique\" ></div><div class=form-group><input type=text class=form-control name=note_frs placeholder=\"Note francais\" ></div><div class=form-group><input type=text class=form-control name=note_ang placeholder=\"Note Anglais\" ></div>";
    }
    else{
        document.getElementById("inputa").innerHTML="<br><div class=form-group><input type=text class=form-control name=note_math placeholder=\"Note Mathematique\" ></div><div class=form-group><input type=text class=form-control name=note_pc placeholder=\"Note pc\" ></div><div class=form-group><input type=text class=form-control name=note_ang placeholder=\"Note Anglais\" ></div>";
    }
}
function filtre_array(nom_ecole,region_ecole,district_ecole){
    var table = document.getElementsByClassName('table')[0];
    var tr = table.getElementsByTagName("tr");
    for (i = 1; i < tr.length; i++) {
        tr[i].style.display = "none";    
        td = tr[i].getElementsByTagName("td");
        if(td.length==4){
            console.log(td[0].innerHTML.toUpperCase()+"     "+nom_ecole.toUpperCase()+"     "+td[1].innerHTML.toUpperCase()+"       "+region_ecole.toUpperCase()+"        "+td[2].innerHTML.toUpperCase()+"      "+district_ecole.toUpperCase());
            if( td[0].innerHTML.toUpperCase().indexOf(nom_ecole.toUpperCase())> -1 && td[1].innerHTML.toUpperCase().indexOf(region_ecole.toUpperCase())> -1 && td[2].innerHTML.toUpperCase().indexOf(district_ecole.toUpperCase())> -1 ){
                tr[i].style.display = "";
            }
        }
        // for (var j = 0; j < td.length; j++) {
        //   cell = tr[i].getElementsByTagName("td")[j];
        //   if (cell) {
        //     if (cell.innerHTML.toUpperCase().indexOf(filter) > -1) {
            //   break;
        //     } 
        //   }
        // }
    }
}
function add_values(id){
    document.getElementById('value_ecole').value=id;
}

