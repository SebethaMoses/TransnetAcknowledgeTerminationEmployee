
//signature2
custom["d10f16d5-80cb-b98d-8738-1a63e4ef5aa6"]={
    onChange: function() {
        form.form.comments.required = true;
        form.form.comments.disabled = false;
    }
}
//--end

//comments
custom["bb49c1f2-7184-2a3a-e7cf-d44c7b48dfd5"]={
    disabled: true
}
//--end

//officer_signature
custom["2090b31e-0faa-757b-386b-a75f552799e0"]={
    onChange: function() {
        form.form.comments.required = true;
        form.form.comments.disabled = false;
    }
}
//--end

//termination_date
custom["e335692d-1977-4d67-c251-b334fda86c44"]={
    validations: 'future'
}

//--end

//reignation_reasons
custom["e202956a-d105-6a6d-ca93-b63c5f01a722"]={
    onChange: function() {
        if (this.value == 'Other') {
            form.form.other_reasons.required = true;
        } else
            form.form.other_reasons.required = false;
    }
}

//--end
