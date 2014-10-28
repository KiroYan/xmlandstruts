
(function($, undefined) {
    $(function() {
        var $select_inStock = $('#form #inStock');
        var $input_price = $('#price');
 
        $select_inStock.change(function() {
            if ($(this).val() == 'false') {
                $input_price.prop('disabled', true);
                $input_price.val('');
            } else {
                $input_price.prop('disabled', false);
            }
        });
 
        if ($select_inStock.val() == 'false') {
            $input_price.prop('disabled', true);
            $input_price.val('');
        }
 
        $('#form').submit(function () {
            $(this).find('.error').remove();
 
            $(this).find('input').not('#model, #dateOfIssue, #price').each(function() {
               if (!$.trim($(this).val())) {
                  $(this).parent().prepend(
                      $('<div>')
                          .attr({'class': 'error'})
                          .text($(this).attr('name') + ' must not be empty!')
                  );
               }
            });
 
            var DATE_PATTERN = /^((29-02-(((([02468][048])|([13579][26]))00)|(\d{2}(([02468][48])|([2468][048])|([13579][26])))))|(((((0[1-9])|(1\d)|(2[0-8]))-02)|(((0[1-9])|(1\d)|(30))-((04)|(06)|(09)|(11)))|(((0[1-9])|(1\d)|([3][0-1]))-((01)|(03)|(05)|(07)|(08)|(10)|(12))))-\d{4}))$/;
            var MODEL_PATTERN = /^([A-Za-z]{2}\d{3})$/;
            var CURRENCY_PATTERN = /^\d+(\.\d\d)?$/;
 
            var date_obj = {
                PATTERN: DATE_PATTERN,
                error_message: 'Date must be in dd-mm-yyyy format!',
                $input: $('#dateOfIssue')
            };
 
            var model_obj = {
                PATTERN: MODEL_PATTERN,
                error_message: 'Model must be in 2latter3number format!',
                $input: $('#model')
            };
 
            var price_obj = {
                PATTERN: CURRENCY_PATTERN,
                error_message: 'Price must be in currency format!',
                $input: $('#price')
            };
 
            $.each([date_obj, model_obj, price_obj], function() {
                if(this.$input.attr('id') == 'price' && $select_inStock.val() == 'false') {
                    return;
                }
 
                if (!this.PATTERN.test($.trim(this.$input.val()))) {
                    this.$input.parent().prepend(
                          $('<div>')
                              .attr({'class': 'error'})
                              .text(this.error_message)
                    );
                }
            });
 
            if ($(this).find('.error').length) {
                return false;
            }
        });
})})(jQuery);

/*function cancel() { 
	return window.history.go(-1); 
};

function blockIfFalse(value) { 
	if (value == "true")
		document.getElementById("price").disabled = false;
	if (value == "false")
		document.getElementById("price").disabled = true;
};*/