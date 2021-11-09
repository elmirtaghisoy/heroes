// get data for modal
function selectOperation(opp, response) {
    if (opp === "delete") {
        $("#delSocModalBody").html(response);
    } else if (opp === "update") {
        $("#updSocModalBody").html(response);
    } else if (opp === "add") {
        $("#addSocModalBody").html(response);
    }
    window.reload();
}

function init(id, opp, obj) {
    const data = {
        id: id
    };
    $.ajax({
        url: "categorization/" + obj,
        type: "GET",
        dataType: "html",
        data: data,
        success: function (response) {
            selectOperation(opp, response);
        },
        error: function () {
            alert("error");
            window.reload();
        }
    });
}

// modal operations
$(document).ready(function () {

    $(".delete-button").click(function () {
        $('#delete-modal').modal('show');
    });

    $(".save-delete-button").click(function () {
        window.refresh();
    });

    // standard open close success error modals
    if ($('#success').text() !== "") {
        $('#success-modal').modal('show');
    }

    if ($('#error').text() !== "") {
        $('#error-modal').modal('show');
    }

    $("#close-error-modal").click(function () {
        $('#error-modal').modal('hide');
    });

    $("#close-success-modal").click(function () {
        $('#success-modal').modal('hide');
    });
    //


    // field error modals (WAR)
    if ($('#war-error').val() != null) {
        $('#field-error-modal').modal('show');
    }
    $("#close-field-error-modal").click(function () {
        $('#field-error-modal').modal('hide');
    });
    //

});


// for fading buttons
var warFirstStep = true;
var warPrev;
var warData;

function activateUpdateButtonWar(id) {
    if (warFirstStep) {
        $('#war-update-button-' + id).fadeOut(1);
        $('#war-update-form-' + id).fadeIn(1);
        $('#war-update-input-' + id).prop('disabled', false);
        warData = $('#war-update-input-' + id).val();
        warFirstStep = false;
        warPrev = id;
    } else if (warFirstStep === false && id !== warPrev) {
        $('#war-update-button-' + warPrev).fadeIn(1);
        $('#war-update-form-' + warPrev).fadeOut(1);
        $('#war-update-input-' + warPrev).prop('disabled', true);
        $('#war-update-input-' + warPrev).val(warData);

        $('#war-update-button-' + id).fadeOut(1);
        $('#war-update-form-' + id).fadeIn(1);
        $('#war-update-input-' + id).prop('disabled', false);
        warData = $('#war-update-input-' + id).val();
        warPrev = id;
    }
}