
document.addEventListener("DOMContentLoaded", function () {
    const { username, routineId } = window.qrScannerConfig;

    let hasScanned = false; // Flag to ensure a QR code is processed only once

    function onScanSuccess(decodedText) {
        if (hasScanned) return; // Ignore further scans if already processed
        hasScanned = true; // Set the flag to prevent reprocessing

        document.getElementById("scan-result").innerText = `Scanned: ${decodedText}`;
        console.log(`Scanned QR Code: ${decodedText}`);

        // Send to backend via POST
        const apiUrl = `/scanner/${username}/${routineId}`;
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ qrCode: decodedText })
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = response.url; // Redirect if the backend provides a URL
                } else {
                    response.text().then(text => alert(`Failed: ${text}`));
                }
            })
            .catch(err => {
                console.error("Error:", err);
                alert('An error occurred while processing the QR Code.');
            })
            .finally(() => {
                // Optionally reset the flag after a delay
                setTimeout(() => {
                    hasScanned = false;
                }, 5000); // Allow scanning another QR code after 5 seconds
            });
    }

    let html5QrCode = new Html5Qrcode("reader");
    html5QrCode.start({ facingMode: "environment" }, { fps: 10, qrbox: 250 }, onScanSuccess);

    // Event listener for manual entry form submission
    document.getElementById('manual-entry-form').addEventListener('submit', function(event) {
        event.preventDefault();
        const gymId = document.getElementById('gym-id').value;

        // Send to backend via POST
        const apiUrl = `/scanner/${username}/${routineId}/manual`;
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ gymId: gymId })
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = response.url; // Redirect if the backend provides a URL
                } else {
                    response.text().then(text => alert(`Failed: ${text}`));
                }
            })
            .catch(err => {
                console.error("Error:", err);
                alert('An error occurred while submitting the Gym ID.');
            });
    });

});
