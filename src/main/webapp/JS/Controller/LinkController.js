/**
 * @description Generating the short(Tiny) url.
 * @function getShortUrl
 */


function getShortUrl(){
    $.ajax({
        type: 'POST',
        url: '/short/link',
        contentType:"application/x-www-form-urlencoded; charset:UTF-8",
        data: {
            longUrl : document.getElementById("urlText").value
        },
        success: function (response) {
            console.log("Response :: "+response);
            $("#urlForm").data('formValidation').resetForm(true);
            var a = "<a href='"+response+"' target='_blank'>"+response+"</a>";
            $('#result').html("<h2>Result Link :</h2><a href='"+response+"' target='_blank'>"+response+"</a>");

        },
        error: function (response) {
            console.log("Error :: ## :: "+response);
        }
    });
}