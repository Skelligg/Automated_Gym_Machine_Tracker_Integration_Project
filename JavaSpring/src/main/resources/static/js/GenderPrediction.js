
const data = rawData.data || [];

// Machine Usage Data
console.log('rawData:', rawData);
console.log('rawData.data:', rawData.data);


const labels = data.map(entry=>entry.gender || 'Unknown')
const dedicated = data.map(entry => entry.Dedicated || 0);

const likelyToGiveUp = data.map(entry => entry["Likely to Give up"] || 0);

console.log("Labels:", labels);
console.log("Dedicated (%):", dedicated);
console.log("Likely to Give Up (%):", likelyToGiveUp);


const genderAnalyticsChart = document.getElementById('genderChart').getContext('2d');
new Chart(genderAnalyticsChart, {
    type: 'bar',
    data: {
        labels: labels,
        datasets: [
            {
                label: 'Dedicated (%)',
                data: dedicated,
                backgroundColor: 'rgba(75, 192, 192)',
                stack: 'stack1',
            },
            {
                label: 'Likely to Give Up (%)',
                data: likelyToGiveUp,
                backgroundColor: 'rgba(255, 99, 132)',
                stack: 'stack1',
            }
        ]
    },
    options: {
        responsive: true,
        scales: {
            x: {
                beginAtZero: true
            },
            y: {
                stacked: true,
                beginAtZero: true,
                max: 100
            }
        },
        plugins: {
            legend: {
                position: 'top',
            }
        }
    }
});