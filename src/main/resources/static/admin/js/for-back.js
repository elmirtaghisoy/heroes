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

$(document).ready(function () {
    $(".delete-button").click(function () {
        $('#delete-modal').modal('show');
    });
    $(".save-delete-button").click(function () {
        window.refresh();
    });
});


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