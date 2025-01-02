document.addEventListener("DOMContentLoaded", function () {
    const { username, routineId } = window.qrScannerConfig;

    let hasScanned = false; // Flag to ensure a QR code is processed only once

    function onScanSuccess(decodedText) {
        if (hasScanned) return;

        // checks if the QR code is valid
        if (!decodedText || typeof decodedText !== "string" || decodedText.trim() === "") {
            console.warn("Invalid or empty QR code detected. Ignoring scan.");
            document.getElementById("scan-result").innerText = "No valid QR code detected. Please try again.";
            return; // Exit early if the QR code result is invalid
        }

        hasScanned = true; // Set the flag to prevent reprocessing
        document.getElementById("scan-result").innerText = `Scanned: ${decodedText}`;
        console.log(`Scanned QR Code: ${decodedText}`);

        // Send to backend via POST
        const apiUrl = `/scanner/${username}/${routineId}`;
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ qrCode: decodedText }),
        })
            .then((response) => {
                if (response.ok) {
                    window.location.href = response.url; // Redirect if the backend provides a URL
                } else {
                    response.text().then((text) => alert(`Failed: ${text}`));
                }
            })
            .catch((err) => {
                console.error('Error processing the QR Code:', err);
                alert('An error occurred while processing the QR Code.');
            })
            .finally(() => {
                // Optionally reset the flag after a delay
                setTimeout(() => {
                    hasScanned = false;
                }, 5000);
            });
    }

    let html5QrCode = new Html5Qrcode("reader");

    // Start QR Code scanning with error handling
    html5QrCode
        .start({ facingMode: 'environment' }, { fps: 10, qrbox: 250 },
            onScanSuccess).catch((err) => {
            console.error('Camera access failed:', err);
            document.getElementById('scan-result').innerText =
                'Camera access failed. Please try manual entry.';
        });

    // Event listener to toggle manual entry form visibility
    const manualEntryForm = document.getElementById('manual-entry-form');

    // Event listener for manual entry form submission
    manualEntryForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const gymId = document.getElementById('gym-id').value;

        if (!gymId) {
            alert('Gym ID cannot be empty.');
            return;
        }

        // Send to backend via POST
        const apiUrl = `/scanner/${username}/${routineId}/manual`;
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ gymId: gymId }),
        })
            .then((response) => {
                if (response.ok) {
                    window.location.href = response.url; // Redirect if the backend provides a URL
                } else {
                    response.text().then((text) => alert(`Failed: ${text}`));
                }
            })
    });
});
