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

    if ($('#war-error').val() != null) {
        $('#field-error-modal').modal('show');
    }

    if ($('#success').text() !== "") {
        $('#success-modal').modal('show');
    }

    if ($('#error').text() !== "") {
        $('#error-modal').modal('show');
    }

    $("#close-field-error-modal").click(function () {
        $('#field-error-modal').modal('hide');
    });

    $("#close-error-modal").click(function () {
        $('#error-modal').modal('hide');
    });

    $("#close-success-modal").click(function () {
        $('#success-modal').modal('hide');
    });

});


// for fading buttons
var firstStep = true;
var prev;
var data;

function activateUpdateButton(id) {
    if (firstStep) {
        $('#war-update-button-' + id).fadeOut(1);
        $('#war-update-form-' + id).fadeIn(1);
        $('#war-update-input-' + id).prop('disabled', false);
        data = $('#war-update-input-' + id).val();
        firstStep = false;
        prev = id;
    } else if (firstStep === false && id !== prev) {
        $('#war-update-button-' + prev).fadeIn(1);
        $('#war-update-form-' + prev).fadeOut(1);
        $('#war-update-input-' + prev).prop('disabled', true);
        $('#war-update-input-' + prev).val(data);
        $('#war-update-button-' + id).fadeOut(1);
        $('#war-update-form-' + id).fadeIn(1);
        $('#war-update-input-' + id).prop('disabled', false);
        data = $('#war-update-input-' + id).val();
        prev = id;
    }
}