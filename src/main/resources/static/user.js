function removeInsurance(userId, insuranceId) {
    if (!confirm('Opravdu chcete odstranit toto pojištění?')) {
        return;  // Pokud uživatel nepotvrdí, nic se nestane
    }

    fetch('/insurances/delete-insurance', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({ userId: userId, insuranceId: insuranceId })
    }).then(response => {
        if (response.ok) {
            location.reload();  // Obnoví stránku, aby se aktualizoval seznam pojištění
        } else {
            alert('Nepodařilo se odstranit pojištění.');
        }
    }).catch(error => {
        console.error('Chyba při odesílání požadavku:', error);
    });
}
