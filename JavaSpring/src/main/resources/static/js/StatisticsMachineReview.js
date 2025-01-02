document.addEventListener("DOMContentLoaded", function () {
    // Display the current date
    const currentDate = new Date();
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    document.getElementById('current-date').textContent = currentDate.toLocaleDateString(undefined, options);

    // Machine Usage Data
    console.log('Machine Usage Labels:', machineTopUsageKeys);
    console.log('Machine Usage Data:', machineTopUsageValues);

    const ctx1 = document.getElementById('machineUsageChart').getContext('2d');
    const machineUsageChart = new Chart(ctx1, {
        type: 'bar',
        data: {
            labels: machineTopUsageKeys,
            datasets: [{
                label: 'Usage',
                data: machineTopUsageValues,
                backgroundColor: 'rgba(0, 0, 0, 0.8)'
            }]
        }
    });

    // Total Activity Data
    console.log('Total Activity Labels:', gymUsageKeys);
    console.log('Total Activity Data:', gymUsageValues);

    const ctx2 = document.getElementById('totalActivityChart').getContext('2d');
    new Chart(ctx2, {
        type: 'line',
        data: {
            labels: gymUsageKeys,
            datasets: [{
                label: 'Total Activity (Units)',
                data: gymUsageValues,
                borderColor: 'rgba(54, 162, 235, 1)',
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                tension: 0.3,
                fill: true
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                x: {
                    title: {
                        display: true,
                        text: 'Time of Day'
                    }
                },
                y: {
                    title: {
                        display: true,
                        text: 'Activity Level'
                    },
                    beginAtZero: true
                }
            }
        }
    });

    // Subscriptions Data
    console.log('Subscriptions Labels:', gymUsageMonthlyKeys);
    console.log('Subscriptions Data:', gymUsageMonthlyValues);

    const ctx3 = document.getElementById('subscriptionsChart').getContext('2d');
    new Chart(ctx3, {
        type: 'line',
        data: {
            labels: gymUsageMonthlyKeys,
            datasets: [
                {
                    label: 'Membership',
                    data: gymUsageMonthlyValues,
                    backgroundColor: 'rgba(128, 128, 128, 1)',
                    borderColor: 'rgba(128, 128, 128, 1)',
                    borderWidth: 2,
                    pointBackgroundColor: 'rgba(128, 128, 128, 1)',
                    pointRadius: 5,
                    fill: false
                },
            ]
        },
        options: {
            responsive: true,
            scales: {
                x: {
                    type: 'category',
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Dec'],
                    title: {
                        display: true,
                        text: 'Month'
                    }
                },
                y: {
                    title: {
                        display: true,
                        text: 'Subscriptions'
                    },
                    beginAtZero: true
                }
            }
        }
    });

});
