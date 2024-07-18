function confirmDeletion(formId) {
    if (confirm('Opravdu chcete toto pojištění odstranit?')) {
        document.getElementById(formId).submit();
    }
}
