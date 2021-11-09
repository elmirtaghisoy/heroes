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
        $('#field-error-modal-war').modal('show');
    }
    $("#close-field-error-modal-war").click(function () {
        $('#field-error-modal-war').modal('hide');
    });

//////////////////////////////////////////

    if ($('#rank-error').val() != null) {
        $('#field-error-modal-rank').modal('show');
    }
    $("#close-field-error-modal-rank").click(function () {
        $('#field-error-modal-rank').modal('hide');
    });

//////////////////////////////////////////

    if ($('#reward-error').val() != null) {
        $('#field-error-modal-reward').modal('show');
    }
    $("#close-field-error-modal-reward").click(function () {
        $('#field-error-modal-reward').modal('hide');
    });

///////////////////////////////////////////////////

    if ($('#postCategory-error').val() != null) {
        $('#field-error-modal-postCategory').modal('show');
    }
    $("#close-field-error-modal-postCategory").click(function () {
        $('#field-error-modal-postCategory').modal('hide');
    });

///////////////////////////////////////////

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

var rankFirstStep = true;
var rankPrev;
var rankData;

function activateUpdateButtonRank(id) {
    if (rankFirstStep) {
        $('#rank-update-button-' + id).fadeOut(1);
        $('#rank-update-form-' + id).fadeIn(1);
        $('#rank-update-input-' + id).prop('disabled', false);
        rankData = $('#rank-update-input-' + id).val();
        rankFirstStep = false;
        rankPrev = id;
    } else if (rankFirstStep === false && id !== rankPrev) {
        $('#rank-update-button-' + rankPrev).fadeIn(1);
        $('#rank-update-form-' + rankPrev).fadeOut(1);
        $('#rank-update-input-' + rankPrev).prop('disabled', true);
        $('#rank-update-input-' + rankPrev).val(rankData);
        $('#rank-update-button-' + id).fadeOut(1);
        $('#rank-update-form-' + id).fadeIn(1);
        $('#rank-update-input-' + id).prop('disabled', false);
        rankData = $('#rank-update-input-' + id).val();
        rankPrev = id;
    }
}

var postCategoryFirstStep = true;
var postCategoryPrev;
var postCategoryData;

function activateUpdateButtonPostCategory(id) {
    if (postCategoryFirstStep) {
        $('#postCategory-update-button-' + id).fadeOut(1);
        $('#postCategory-update-form-' + id).fadeIn(1);
        $('#postCategory-update-input-' + id).prop('disabled', false);
        postCategoryData = $('#postCategory-update-input-' + id).val();
        postCategoryFirstStep = false;
        postCategoryPrev = id;
    } else if (postCategoryFirstStep === false && id !== postCategoryPrev) {
        $('#postCategory-update-button-' + postCategoryPrev).fadeIn(1);
        $('#postCategory-update-form-' + postCategoryPrev).fadeOut(1);
        $('#postCategory-update-input-' + postCategoryPrev).prop('disabled', true);
        $('#postCategory-update-input-' + postCategoryPrev).val(postCategoryData);
        $('#postCategory-update-button-' + id).fadeOut(1);
        $('#postCategory-update-form-' + id).fadeIn(1);
        $('#postCategory-update-input-' + id).prop('disabled', false);
        postCategoryData = $('#postCategory-update-input-' + id).val();
        postCategoryPrev = id;
    }
}

var rewardFirstStep = true;
var rewardPrev;
var rewardData;

function activateUpdateButtonReward(id) {
    if (rewardFirstStep) {
        $('#reward-update-button-' + id).fadeOut(1);
        $('#reward-update-form-' + id).fadeIn(1);
        $('#reward-update-input-' + id).prop('disabled', false);
        rewardData = $('#reward-update-input-' + id).val();
        rewardFirstStep = false;
        rewardPrev = id;
    } else if (rewardFirstStep === false && id !== rewardPrev) {
        $('#reward-update-button-' + rewardPrev).fadeIn(1);
        $('#reward-update-form-' + rewardPrev).fadeOut(1);
        $('#reward-update-input-' + rewardPrev).prop('disabled', true);
        $('#reward-update-input-' + rewardPrev).val(rewardData);
        $('#reward-update-button-' + id).fadeOut(1);
        $('#reward-update-form-' + id).fadeIn(1);
        $('#reward-update-input-' + id).prop('disabled', false);
        rewardData = $('#reward-update-input-' + id).val();
        rewardPrev = id;
    }
}