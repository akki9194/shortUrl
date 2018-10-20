/**
 * @class Validation Class
 * @classdesc fire action after validating the field.
 * @constructor
 */


var LinkValidtion = function () {

    LinkValidtion.prototype.activate = function () {
        $('#urlForm')
            .formValidation({
                framework: 'bootstrap',
                excluded: ':disabled',
                icon: {
                    valid: 'fa fa-check',
                    invalid: 'fa fa-times',
                    validating: 'fa fa-refresh'
                },
                fields: {
                    urlText: {
                        validators: {
                            notEmpty: {
                                message: 'this is required'
                            }
                        }
                    }
                }
            })
            .on('err.field.fv', function(e, data) {
                // $(e.target)  --> The field element
                // data.fv      --> The FormValidation instance
                // data.field   --> The field name
                // data.element --> The field element
                if (data.fv.getSubmitButton()) {
                    data.fv.disableSubmitButtons(false);
                }
            })
            .on('success.form.fv', function(e, data) {
                // Prevent form submission
                e.preventDefault();
                var $form = $(e.target),
                    $button = $form.data('formValidation').getSubmitButton(),
                    fv    = $form.data('formValidation');
                fv.disableSubmitButtons(false);
                getShortUrl();
            })
    }
};