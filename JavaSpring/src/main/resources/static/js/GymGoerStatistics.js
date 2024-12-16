let chart;


function displayChart(chartType, clickedLabel,gymGoerId,machineId){

    //remove the active class from the the other button
    document.querySelectorAll('.btn').forEach(button => button.classList.remove('active'));

    //adding the active class to the clicked one
    clickedLabel.classList.add('active');

    fetchChartData(chartType, gymGoerId, machineId);

    //fetching the chart and checking what type should it be
    function fetchChartData(chartType,gymGoerId, machineId) {


        fetch(`/getChartData/${chartType}?gymGoerId=${gymGoerId}&machineId=${machineId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('failed to load')
                }
                        return response.json();
            }).then(data => {
                //sort the data because it appears the newest on left
                // data.sort((a,b)=> new Date(a.date)-new Date(b.date))

                const labels = data.map(entry => entry.date);
                const chartData  = data.map(entry => {
                    if (chartType === 'weights') return entry.weightCount;
                    if (chartType === 'volume') return entry.volumeD;
                    if (chartType === 'reps') return entry.repCountNumb;
                })

            console.log(data)

            // removing the previous chart
            if (chart) chart.destroy();

            console.log('Labels:', labels);
            console.log('Chart Data:', chartData);

            //getting the max value from the chart so it creates a border for better view
            const maxDataValue = Math.max(...chartData)
            const yMax = maxDataValue + 15;

            // chart based on the pressed button
            const ctx = document.getElementById('chartCanvas').getContext('2d');
            chart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: labels,
                    datasets: [{
                        label: getChartLabel(chartType),
                        data: chartData,
                        borderColor: 'rgb(7,1,1)',
                        backgroundColor: 'rgb(1,1,30)',
                        borderWidth: 2,
                        fill: false,
                        tension: 0.4
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        x: {
                            title: {
                                display: true,
                                //the name of the x-date
                                text: 'Date'
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                // the name of the Y-axis
                                text: getYAxisLabel(chartType)
                            },
                            min: 0,
                            max: yMax
                        }
                    }
                }
            });
        }).catch(error => {
            console.error('There was an error to find the data:', error);
            alert('There was an error fetching the data');
        });
    }
    //
    function getChartLabel(chartType) {
        switch (chartType) {
            case 'weights': return 'Weight Lifted';
            case 'volume':return 'Volume Lifted';
            case 'reps':return 'Total Reps';
            default: return 'Data';
        }
    }
    function getYAxisLabel(chartType) {
        switch (chartType) {
            case 'weights':return 'Weight (kg)';
            case 'volume':return 'Volume (kg)';
            case 'reps':return 'Reps';
            default:return 'Value';
        }
    }

}