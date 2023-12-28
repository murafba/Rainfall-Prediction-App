package fisTsukamoto;

import geneticAlgorithm.GeneticAlgorithm;


public class FISTsukamoto extends GeneticAlgorithm{
    // Temperature upper value
    private double temperatureColdUpperValue;
    private double temperatureWarmUpperValue;
    private double temperatureHotUpperValue;
    // Humidity upper value
    private double humidityDryUpperValue;
    private double humidityWetUpperValue;
    private double humidityMoistUpperValue;
    // Air pressure upper value
    private double airPressureLowUpperValue;
    private double airPressureMediumUpperValue;
    private double airPressureHighUpperValue;
    // Wind velocity upper value
    private double windVelocityMediumUpperValue;
    private double windVelocityFastUpperValue;
    private double windVelocityVeryFastUpperValue;

    // Mu value each variable
    // Temperature
    private double muColdTemperature;
    private double muWarmTemperature;
    private double muHotTemperature;
    // Humidity
    private double muDryHumidity;
    private double muWetHumidity;
    private double muMoistHumidity;
    // Air pressure
    private double muLowAirPressure;
    private double muMediumAirPressure;
    private double muHighAirPressure;
    // Wind velocity
    private double muMediumWindVelocity;
    private double muFastWindVelocity;
    private double muVeryFastWindVelocity;

    // Fuzzification variable
    private double[][][] fuzzTemperature;
    private double[][][] fuzzTemperatureOffspring;
    private double[][][] fuzzTemperatureMutant;
    private double[][][] fuzzHumidity;
    private double[][][] fuzzHumidityOffspring;
    private double[][][] fuzzHumidityMutant;
    private double[][][] fuzzAirPressure;
    private double[][][] fuzzAirPressureOffspring;
    private double[][][] fuzzAirPressureMutant;
    private double[][][] fuzzWindVelocity;
    private double[][][] fuzzWindVelocityOffspring;
    private double[][][] fuzzWindVelocityMutant;

    // Defuzzification: Fire strength and zi value
    private double[][][] fireStrength;
    private double[][][] fireStrengthOffspring;
    private double[][][] fireStrengthMutant;
    private double[][][] ziValue;
    private double[][][] ziValueOffspring;
    private double[][][] ziValueMutant;
    private double[][] totalFireStrength;
    private double[][] totalFireStrengthOffspring;
    private double[][] totalFireStrengthMutant;
    private double[][] totalZiValue;
    private double[][] totalZiValueOffspring;
    private double[][] totalZiValueMutant;
    private double[][] totalMiuMultiplyZi;
    private double[][] totalMiuMultiplyZiOffspring;
    private double[][] totalMiuMultiplyZiMutant;

    // variables in MAPE
    private final double[] actualData =
            {
                    326.00,
                    311.00,
                    317.00,
                    302.00,
                    262.00,
                    113.00,
                    68.00,
                    52.00,
                    131.00,
                    138.00,
                    244.00,
                    231.00,
                    176.00,
                    307.40,
                    251.00,
                    349.50,
                    166.90,
                    143.00,
                    96.00,
                    40.00,
                    64.90,
                    75.90,
                    153.00,
                    242.20,
                    286.00,
                    298.50,
                    367.90,
                    396.50,
                    264.70,
                    188.00,
                    126.00,
                    48.60,
                    152.00,
                    251.10,
                    333.60,
                    228.00,
                    235.70,
                    181.40,
                    251.90,
                    127.60,
                    144.80,
                    60.00,
                    124.70,
                    183.00,
                    229.00,
                    118.10,
                    421.40,
                    285.50,
                    241.00,
                    155.00,
                    192.00,
                    589.00,
                    117.00,
                    247.00,
                    52.00,
                    107.00,
                    180.00,
                    477.00,
                    211.00,
                    266.00,

            };
    private double[] MAPE;
    private double[] MAPEOffspring;
    private double[] MAPEMutant;
    private double[][] APE;
    private double[][] APEOffspring;
    private double[][] APEMutant;

    /*
    CONSTRUCTOR
     */
    public FISTsukamoto(int popSize, int generationSize, double cr, double mr){
        super(popSize, generationSize, cr, mr);
        this.temperatureColdUpperValue = 26;
        this.temperatureWarmUpperValue = 27.5;
        this.temperatureHotUpperValue = 29;
        this.humidityDryUpperValue = 63;
        this.humidityWetUpperValue = 75;
        this.humidityMoistUpperValue = 85;
        this.airPressureLowUpperValue = 1008.5;
        this.airPressureMediumUpperValue = 1011;
        this.airPressureHighUpperValue = 1013;
        this.windVelocityMediumUpperValue = 2;
        this.windVelocityFastUpperValue = 4;
        this.windVelocityVeryFastUpperValue = 6.5;

        this.fuzzTemperature = new double[popSize][60][3];
        this.fuzzHumidity = new double[popSize][60][3];
        this.fuzzAirPressure = new double[popSize][60][3];
        this.fuzzWindVelocity = new double[popSize][60][3];

        this.fuzzTemperatureOffspring = new double[(int) Math.ceil(cr * popSize)][60][3];
        this.fuzzTemperatureMutant = new double[(int) Math.ceil(mr * popSize)][60][3];
        this.fuzzHumidityOffspring = new double[(int) Math.ceil(cr * popSize)][60][3];
        this.fuzzHumidityMutant = new double[(int) Math.ceil(mr * popSize)][60][3];
        this.fuzzAirPressureOffspring = new double[(int) Math.ceil(cr * popSize)][60][3];
        this.fuzzAirPressureMutant = new double[(int) Math.ceil(mr * popSize)][60][3];
        this.fuzzWindVelocityOffspring = new double[(int) Math.ceil(cr * popSize)][60][3];
        this.fuzzWindVelocityMutant = new double[(int) Math.ceil(mr * popSize)][60][3];

        this.fireStrength = new double[popSize][60][81];
        this.ziValue = new double[popSize][60][81];
        this.totalFireStrength = new double[popSize][60];

        this.fireStrengthOffspring = new double[(int) Math.ceil(cr * popSize)][60][81];
        this.fireStrengthMutant = new double[(int) Math.ceil(mr * popSize)][60][81];
        this.ziValueOffspring = new double[(int) Math.ceil(cr * popSize)][60][81];
        this.ziValueMutant = new double[(int) Math.ceil(mr * popSize)][60][81];
        this.totalFireStrengthOffspring = new double[(int) Math.ceil(cr * popSize)][60];
        this.totalFireStrengthMutant = new double[(int) Math.ceil(mr * popSize)][60];

        this.totalZiValue = new double[popSize][60];
        this.totalMiuMultiplyZi = new double[popSize][60];
        this.totalZiValueOffspring = new double[(int) Math.ceil(cr * popSize)][60];
        this.totalZiValueMutant = new double[(int) Math.ceil(mr * popSize)][60];
        this.totalMiuMultiplyZiOffspring = new double[(int) Math.ceil(cr * popSize)][60];
        this.totalMiuMultiplyZiMutant = new double[(int) Math.ceil(mr * popSize)][60];

        this.MAPE = new double[popSize];
        this.MAPEOffspring = new double[(int) Math.ceil(cr * popSize)];
        this.MAPEMutant = new double[(int) Math.ceil(mr * popSize)];
        this.APE = new double[popSize][60];
        this.APEOffspring = new double[(int) Math.ceil(cr * popSize)][60];
        this.APEMutant = new double[(int) Math.ceil(mr * popSize)][60];
    }

    public FISTsukamoto(int popSize, int generationSize, double cr, double mr, double temperatureColdUpperValue, double temperatureWarmUpperValue,
                        double temperatureHotUpperValue, double humidityDryUpperValue, double humidityWetUpperValue, double humidityMoistUpperValue,
                        double airPressureLowUpperValue, double airPressureMediumUpperValue, double airPressureHighUpperValue,
                        double windVelocityMediumUpperValue, double windVelocityFastUpperValue, double windVelocityVeryFastUpperValue) {
        super(popSize,generationSize, cr, mr);
        this.temperatureColdUpperValue = temperatureColdUpperValue;
        this.temperatureWarmUpperValue = temperatureWarmUpperValue;
        this.temperatureHotUpperValue = temperatureHotUpperValue;
        this.humidityDryUpperValue = humidityDryUpperValue;
        this.humidityWetUpperValue = humidityWetUpperValue;
        this.humidityMoistUpperValue = humidityMoistUpperValue;
        this.airPressureLowUpperValue = airPressureLowUpperValue;
        this.airPressureMediumUpperValue = airPressureMediumUpperValue;
        this.airPressureHighUpperValue = airPressureHighUpperValue;
        this.windVelocityMediumUpperValue = windVelocityMediumUpperValue;
        this.windVelocityFastUpperValue = windVelocityFastUpperValue;
        this.windVelocityVeryFastUpperValue = windVelocityVeryFastUpperValue;

        this.fuzzTemperature = new double[popSize][60][3];
        this.fuzzHumidity = new double[popSize][60][3];
        this.fuzzAirPressure = new double[popSize][60][3];
        this.fuzzWindVelocity = new double[popSize][60][3];

        this.fuzzTemperatureOffspring = new double[(int) Math.ceil(cr * popSize)][60][3];
        this.fuzzTemperatureMutant = new double[(int) Math.ceil(mr * popSize)][60][3];
        this.fuzzHumidityOffspring = new double[(int) Math.ceil(cr * popSize)][60][3];
        this.fuzzHumidityMutant = new double[(int) Math.ceil(mr * popSize)][60][3];
        this.fuzzAirPressureOffspring = new double[(int) Math.ceil(cr * popSize)][60][3];
        this.fuzzAirPressureMutant = new double[(int) Math.ceil(mr * popSize)][60][3];
        this.fuzzWindVelocityOffspring = new double[(int) Math.ceil(cr * popSize)][60][3];
        this.fuzzWindVelocityMutant = new double[(int) Math.ceil(mr * popSize)][60][3];

        this.fireStrength = new double[popSize][60][81];
        this.ziValue = new double[popSize][60][81];
        this.totalFireStrength = new double[popSize][60];

        this.fireStrengthOffspring = new double[(int) Math.ceil(cr * popSize)][60][81];
        this.fireStrengthMutant = new double[(int) Math.ceil(mr * popSize)][60][81];
        this.ziValueOffspring = new double[(int) Math.ceil(cr * popSize)][60][81];
        this.ziValueMutant = new double[(int) Math.ceil(mr * popSize)][60][81];
        this.totalFireStrengthOffspring = new double[(int) Math.ceil(cr * popSize)][60];
        this.totalFireStrengthMutant = new double[(int) Math.ceil(mr * popSize)][60];

        this.totalZiValue = new double[popSize][60];
        this.totalMiuMultiplyZi = new double[popSize][60];
        this.totalZiValueOffspring = new double[(int) Math.ceil(cr * popSize)][60];
        this.totalZiValueMutant = new double[(int) Math.ceil(mr * popSize)][60];
        this.totalMiuMultiplyZiOffspring = new double[(int) Math.ceil(cr * popSize)][60];
        this.totalMiuMultiplyZiMutant = new double[(int) Math.ceil(mr * popSize)][60];

        this.MAPE = new double[popSize];
        this.MAPEOffspring = new double[(int) Math.ceil(cr * popSize)];
        this.MAPEMutant = new double[(int) Math.ceil(mr * popSize)];
        this.APEOffspring = new double[(int) Math.ceil(cr * popSize)][60];
        this.APEMutant = new double[(int) Math.ceil(mr * popSize)][60];
        this.APE = new double[popSize][60];
    }


    /*
    PHASE 1: EVALUATE MU VALUE EACH VARIABLE
     */

    // Temperature mu value
    @Override
    public void temperatureMuValue(double inputValue) {

        // fire strength mu cold
        if (inputValue <= this.temperatureColdUpperValue) {
            this.muColdTemperature = 1;
        } else if (inputValue < this.temperatureWarmUpperValue) {
            this.muColdTemperature = (this.temperatureWarmUpperValue - inputValue)/(this.temperatureWarmUpperValue - this.temperatureColdUpperValue);
        } else {
            this.muColdTemperature = 0;
        }

        // fire strength mu warm
        if (inputValue <= this.temperatureColdUpperValue || inputValue >= this.temperatureHotUpperValue) {
            this.muWarmTemperature = 0;
        } else if (inputValue <= this.temperatureWarmUpperValue) {
            this.muWarmTemperature = (inputValue - this.temperatureColdUpperValue)/(this.temperatureWarmUpperValue - this.temperatureColdUpperValue);
        } else {
            this.muWarmTemperature = (this.temperatureHotUpperValue - inputValue)/(this.temperatureHotUpperValue - this.temperatureWarmUpperValue);
        }

        // fire strength mu hot
        if (inputValue <= this.temperatureWarmUpperValue) {
            this.muHotTemperature = 0;
        } else if(inputValue < this.temperatureHotUpperValue) {
            this.muHotTemperature = (inputValue - this.temperatureWarmUpperValue)/(this.temperatureHotUpperValue - this.temperatureWarmUpperValue);
        } else {
            this.muHotTemperature = 1;
        }
    }
    // Humidity mu value
    @Override
    public void humidityMuValue(double inputValue) {
        // fire strength mu dry
        if (inputValue <= this.humidityDryUpperValue) {
            this.muDryHumidity = 1;
        } else if (inputValue < this.humidityWetUpperValue) {
            this.muDryHumidity = (this.humidityWetUpperValue - inputValue)/(this.humidityWetUpperValue - this.humidityDryUpperValue);
        } else {
            this.muDryHumidity = 0;
        }

        // fire strength mu wet
        if (inputValue <= this.humidityDryUpperValue || inputValue >= this.humidityMoistUpperValue) {
            this.muWetHumidity = 0;
        } else if (inputValue <= this.humidityWetUpperValue) {
            this.muWetHumidity = (inputValue - this.humidityDryUpperValue)/(this.humidityWetUpperValue - this.humidityDryUpperValue);
        } else {
            this.muWetHumidity = (this.humidityMoistUpperValue - inputValue)/(this.humidityMoistUpperValue - this.humidityWetUpperValue);
        }

        // fire strength mu moist
        if (inputValue <= this.humidityWetUpperValue) {
            this.muMoistHumidity = 0;
        } else if (inputValue < this.humidityMoistUpperValue) {
            this.muMoistHumidity = (inputValue - this.humidityWetUpperValue)/(this.humidityMoistUpperValue - this.humidityWetUpperValue);
        } else {
            this.muMoistHumidity = 1;
        }
    }
    // Air Pressure mu value
    @Override
    public void airPressureMuValue(double inputValue) {
        // fire strength mu low
        if (inputValue <= this.airPressureLowUpperValue) {
            this.muLowAirPressure = 1;
        } else if (inputValue < this.airPressureMediumUpperValue) {
            this.muLowAirPressure = (this.airPressureMediumUpperValue - inputValue)/(this.airPressureMediumUpperValue - this.airPressureLowUpperValue);
        } else {
            this.muLowAirPressure = 0;
        }

        // fire strength mu medium
        if (inputValue <= this.airPressureLowUpperValue || inputValue >= this.airPressureHighUpperValue) {
            this.muMediumAirPressure = 0;
        } else if (inputValue <= this.airPressureMediumUpperValue) {
            this.muMediumAirPressure = (inputValue - this.airPressureLowUpperValue)/(this.airPressureMediumUpperValue - this.airPressureLowUpperValue);
        } else {
            this.muMediumAirPressure = (this.airPressureHighUpperValue - inputValue)/(this.airPressureHighUpperValue - this.airPressureMediumUpperValue);
        }

        // fire strength mu high
        if (inputValue <= this.airPressureMediumUpperValue) {
            this.muHighAirPressure = 0;
        } else if (inputValue < this.airPressureHighUpperValue) {
            this.muHighAirPressure = (inputValue - this.airPressureMediumUpperValue)/(this.airPressureHighUpperValue - this.airPressureMediumUpperValue);
        } else {
            this.muHighAirPressure = 1;
        }
    }
    // Wind velocity mu value
    @Override
    public void windVelocityMuValue(double inputValue) {
        // fire strength mu medium
        if (inputValue <= this.windVelocityMediumUpperValue) {
            this.muMediumWindVelocity = 1;
        } else if (inputValue < this.windVelocityFastUpperValue) {
            this.muMediumWindVelocity = (this.windVelocityFastUpperValue - inputValue)/(this.windVelocityFastUpperValue - this.windVelocityMediumUpperValue);
        } else {
            this.muMediumWindVelocity = 0;
        }

        // fire strength mu fast
        if (inputValue <= this.windVelocityMediumUpperValue || inputValue >= this.windVelocityVeryFastUpperValue) {
            this.muFastWindVelocity = 0;
        } else if (inputValue <= this.windVelocityFastUpperValue) {
            this.muFastWindVelocity = (inputValue - this.windVelocityMediumUpperValue)/(this.windVelocityFastUpperValue - this.windVelocityMediumUpperValue);
        } else {
            this.muFastWindVelocity = (this.windVelocityVeryFastUpperValue - inputValue)/(this.windVelocityVeryFastUpperValue - this.windVelocityFastUpperValue);
        }

        // fire strength mu very fast
        if (inputValue <= this.windVelocityFastUpperValue) {
            this.muVeryFastWindVelocity = 0;
        } else if (inputValue < this.windVelocityVeryFastUpperValue) {
            this.muVeryFastWindVelocity = (inputValue - this.windVelocityFastUpperValue)/(this.windVelocityVeryFastUpperValue - this.windVelocityFastUpperValue);
        } else {
            this.muVeryFastWindVelocity = 1;
        }
    }


    /*
    PHASE 2: EVALUATE FUZZY RULE BASE
     */

    // Membership function prediction to fin zi value from fire strength
    private double mfSunny(double fireStrength){
        return (-55 * fireStrength) + 105;
    }
    private double mfCloudy(double fireStrength){
        return (100 * fireStrength) + 100;
    }
    private double mfLightRain(double fireStrength){
        return (150 * fireStrength) + 200;
    }
    private double mfHeavyRain(double fireStrength){
        return (50 * fireStrength) + 450;
    }

    // Evaluate each rule and produce fire strength
    @Override
    public void evaluateRule() {
        for (int i = 0; i < getPopSize(); i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 81; k++){
                    if (k == 0) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 1) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 2) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 3) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 4) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 5) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 6) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 7) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 8) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 9) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 10) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 11) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 12) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 13) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 14) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 15) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 16) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 17) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 18) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 19) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 20) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 21) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 22) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 23) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 24) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 25) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 26) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 27) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 28) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 29) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 30) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 31) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 32) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 33) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 34) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 35) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 36) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 37) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 38) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 39) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 40) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 41) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 42) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 43) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 44) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 45) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 46) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 47) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 48) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 49) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 50) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 51) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 52) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 53) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 54) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 55) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 56) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 57) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 58) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 59) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 60) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 61) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 62) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 63) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 64) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 65) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 66) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 67) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 68) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 69) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 70) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 71) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 72) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 73) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 74) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 75) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 76) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 77) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 78) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 79) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    }

                }
            }


        }
        for (int j = 0; j < getPopSize(); j++) {
            for (int k = 0; k < 60; k++) {
                for (int l = 0; l < 81; l++){
                    totalFireStrength[j][k] = Double.parseDouble(decimalFormat.format(totalFireStrength[j][k]))
                            +  Double.parseDouble(decimalFormat.format(getFireStrength()[j][k][l]));
                }
            }
        }
    }
    @Override
    public void evaluateRuleOffspring() {
        for (int i = 0; i < (int) Math.ceil(getCr() * getPopSize()); i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 81; k++){
                    if (k == 0) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 1) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 2) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 3) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 4) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 5) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 6) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 7) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 8) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 9) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 10) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 11) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 12) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 13) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 14) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 15) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 16) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 17) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 18) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 19) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 20) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 21) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 22) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 23) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 24) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 25) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 26) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][0], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 27) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 28) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 29) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 30) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 31) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 32) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 33) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 34) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 35) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 36) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 37) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 38) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 39) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 40) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 41) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 42) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 43) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 44) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 45) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 46) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 47) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 48) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 49) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 50) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 51) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 52) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 53) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][1], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 54) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfHeavyRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 55) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 56) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 57) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfLightRain(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 58) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 59) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 60) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 61) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 62) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][2], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 63) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 64) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 65) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 66) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 67) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 68) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 69) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 70) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 71) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][1], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 72) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfCloudy(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 73) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 74) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][0], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 75) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 76) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 77) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][1], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 78) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][0])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else if (k == 79) {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][1])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    } else {
                        fireStrengthOffspring[i][j][k] = Math.min(fuzzTemperatureOffspring[i][j][2], Math.min(fuzzHumidityOffspring[i][j][0], Math.min(fuzzAirPressureOffspring[i][j][2], fuzzWindVelocityOffspring[i][j][2])));
                        ziValueOffspring[i][j][k] = mfSunny(getFireStrengthOffspring()[i][j][k]);
                    }
                }
            }
        }
        for (int j = 0; j < (int) Math.ceil(getCr() * getPopSize()); j++) {
            for (int k = 0; k < 60; k++) {
                for (int l = 0; l < 81; l++){
                    totalFireStrengthOffspring[j][k] = Double.parseDouble(decimalFormat.format(totalFireStrengthOffspring[j][k])) + Double.parseDouble(decimalFormat.format(getFireStrengthOffspring()[j][k][l]));
                }
            }
        }
    }
    @Override
    public void evaluateRuleMutant() {
        for (int i = 0; i < (int) Math.ceil(getMr() * getPopSize()); i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 81; k++){
                    if (k == 0) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 1) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 2) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 3) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 4) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 5) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 6) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 7) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 8) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 9) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 10) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 11) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 12) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 13) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 14) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 15) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 16) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 17) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 18) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 19) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 20) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 21) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 22) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 23) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 24) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 25) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 26) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][0], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 27) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 28) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 29) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 30) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 31) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 32) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 33) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 34) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 35) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 36) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 37) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 38) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 39) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 40) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 41) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 42) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 43) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 44) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 45) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 46) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 47) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 48) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 49) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 50) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 51) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 52) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 53) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][1], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 54) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfHeavyRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 55) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 56) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 57) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfLightRain(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 58) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 59) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 60) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 61) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 62) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][2], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 63) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 64) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 65) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 66) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 67) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 68) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 69) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 70) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 71) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][1], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 72) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfCloudy(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 73) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 74) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][0], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 75) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 76) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 77) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][1], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 78) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][0])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else if (k == 79) {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][1])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    } else {
                        fireStrengthMutant[i][j][k] = Math.min(fuzzTemperatureMutant[i][j][2], Math.min(fuzzHumidityMutant[i][j][0], Math.min(fuzzAirPressureMutant[i][j][2], fuzzWindVelocityMutant[i][j][2])));
                        ziValueMutant[i][j][k] = mfSunny(getFireStrengthMutant()[i][j][k]);
                    }
                }
            }
        }
        for (int j = 0; j < (int) Math.ceil(getMr() * getPopSize()); j++) {
            for (int k = 0; k < 60; k++) {
                for (int l = 0; l < 81; l++){
                    totalFireStrengthMutant[j][k] = Double.parseDouble(decimalFormat.format(totalFireStrengthMutant[j][k])) + Double.parseDouble(decimalFormat.format(getFireStrengthMutant()[j][k][l]));
                }
            }
        }
    }
    @Override
    public void evaluateRuleTsukamoto() {
        for (int i = 0; i < getPopSize(); i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < 81; k++){
                    if (k == 0) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 1) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 2) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 3) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 4) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 5) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 6) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 7) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 8) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 9) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 10) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 11) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 12) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 13) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 14) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 15) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 16) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 17) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 18) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 19) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 20) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 21) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 22) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 23) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 24) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 25) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 26) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][0], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 27) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 28) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 29) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 30) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 31) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 32) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 33) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 34) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 35) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 36) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 37) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 38) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 39) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 40) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 41) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 42) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 43) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 44) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 45) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 46) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 47) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 48) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 49) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 50) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 51) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 52) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 53) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][1], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 54) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfHeavyRain(getFireStrength()[i][j][k]);
                    } else if (k == 55) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 56) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 57) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfLightRain(getFireStrength()[i][j][k]);
                    } else if (k == 58) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 59) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 60) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 61) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 62) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][2], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 63) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 64) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 65) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 66) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 67) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 68) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 69) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 70) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 71) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][1], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 72) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfCloudy(getFireStrength()[i][j][k]);
                    } else if (k == 73) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 74) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][0], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 75) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 76) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 77) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][1], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 78) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][0])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else if (k == 79) {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][1])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    } else {
                        fireStrength[i][j][k] = Math.min(fuzzTemperature[i][j][2], Math.min(fuzzHumidity[i][j][0], Math.min(fuzzAirPressure[i][j][2], fuzzWindVelocity[i][j][2])));
                        ziValue[i][j][k] = mfSunny(getFireStrength()[i][j][k]);
                    }

                }
            }


        }
        for (int j = 0; j < getPopSize(); j++) {
            for (int k = 0; k < 1; k++) {
                for (int l = 0; l < 81; l++){
                    totalFireStrength[j][k] = Double.parseDouble(decimalFormat.format(totalFireStrength[j][k]))
                            +  Double.parseDouble(decimalFormat.format(getFireStrength()[j][k][l]));
                }
            }
        }
    }

    /*
    PHASE 3: DO DEFUZZIFICATION
     */
    @Override
    public void evaluateDefuzzifier(){
        for (int i = 0; i < getPopSize(); i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 81; k++) {
                    // Calculate miu multiply zi
                    totalMiuMultiplyZi[i][j] = totalMiuMultiplyZi[i][j] + (getFireStrength()[i][j][k] * getZiValue()[i][j][k]);
                    totalMiuMultiplyZi[i][j] = Double.parseDouble(decimalFormat.format(totalMiuMultiplyZi[i][j]));
                }

            }
        }
        for (int i = 0; i < getPopSize(); i++) {
            for (int j = 0; j < 60; j++){
                // Evaluate sum of miu multiply zi divide with sum of miu
                totalZiValue[i][j] =   totalMiuMultiplyZi[i][j]/getTotalFireStrength()[i][j];
                totalZiValue[i][j] = Double.parseDouble(decimalFormat.format(totalZiValue[i][j]));
            }

        }
    }
    @Override
    public void evaluateDefuzzifierOffspring(){
        for (int i = 0; i < (int) Math.ceil(getCr() * getPopSize()); i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 81; k++) {
                    // Calculate miu multiply zi
                    totalMiuMultiplyZiOffspring[i][j] = totalMiuMultiplyZiOffspring[i][j] + (getFireStrengthOffspring()[i][j][k] * getZiValueOffspring()[i][j][k]);
                    totalMiuMultiplyZiOffspring[i][j] = Double.parseDouble(decimalFormat.format(totalMiuMultiplyZiOffspring[i][j]));
                }

            }
        }
        for (int i = 0; i < (int) Math.ceil(getCr() * getPopSize()); i++) {
            for (int j = 0; j < 60; j++){
                // Evaluate sum of miu multiply zi divide with sum of miu
                totalZiValueOffspring[i][j] =   totalMiuMultiplyZiOffspring[i][j]/getTotalFireStrengthOffspring()[i][j];
                totalZiValueOffspring[i][j] = Double.parseDouble(decimalFormat.format(totalZiValueOffspring[i][j]));
            }
        }
    }
    @Override
    public void evaluateDefuzzifierMutant(){
        for (int i = 0; i < (int) Math.ceil(getMr() * getPopSize()); i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 81; k++) {
                    // Calculate miu multiply zi
                    totalMiuMultiplyZiMutant[i][j] = totalMiuMultiplyZiMutant[i][j] + (getFireStrengthMutant()[i][j][k] * getZiValueMutant()[i][j][k]);
                    totalMiuMultiplyZiMutant[i][j] = Double.parseDouble(decimalFormat.format(totalMiuMultiplyZiMutant[i][j]));
                }

            }
        }
        for (int i = 0; i < (int) Math.ceil(getMr() * getPopSize()); i++) {
            for (int j = 0; j < 60; j++){
                // Evaluate sum of miu multiply zi divide with sum of miu
                totalZiValueMutant[i][j] =   totalMiuMultiplyZiMutant[i][j]/getTotalFireStrengthMutant()[i][j];
                totalZiValueMutant[i][j] = Double.parseDouble(decimalFormat.format(totalZiValueMutant[i][j]));
            }
        }
    }
    @Override
    public void evaluateDefuzzifierTsukamoto(){
        for (int i = 0; i < getPopSize(); i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < 81; k++) {
                    // Calculate miu multiply zi
                    totalMiuMultiplyZi[i][j] = totalMiuMultiplyZi[i][j] + (getFireStrength()[i][j][k] * getZiValue()[i][j][k]);
                    totalMiuMultiplyZi[i][j] = Double.parseDouble(decimalFormat.format(totalMiuMultiplyZi[i][j]));
                }

            }
        }
        for (int i = 0; i < getPopSize(); i++) {
            for (int j = 0; j < 1; j++){
                // Evaluate sum of miu multiply zi divide with sum of miu
                totalZiValue[i][j] =   totalMiuMultiplyZi[i][j]/getTotalFireStrength()[i][j];
                totalZiValue[i][j] = Double.parseDouble(decimalFormat.format(totalZiValue[i][j]));
            }

        }
    }

    /*
    PHASE 4: CALCULATE APE & MAPE
     */

    // APE
    @Override
    public void evaluateAPE() {
        for (int i = 0; i < getPopSize(); i++){
            for (int j = 0; j < 60; j++) {
                APE[i][j] = APE[i][j]+ (Math.abs((this.actualData[j] - getTotalZiValue()[i][j])/this.actualData[j]) * 100);
                APE[i][j] = Double.parseDouble(decimalFormat.format(APE[i][j]));
            }
        }
    }
    @Override
    public void evaluateAPEOffspring() {
        for (int i = 0; i < (int) Math.ceil(getCr() * getPopSize()); i++){
            for (int j = 0; j < 60; j++) {
                APEOffspring[i][j] = APEOffspring[i][j] + (Math.abs((this.actualData[j] - getTotalZiValueOffspring()[i][j])/this.actualData[j]) * 100);
                APEOffspring[i][j] = Double.parseDouble(decimalFormat.format(APEOffspring[i][j]));
            }
        }
    }
    @Override
    public void evaluateAPEMutant() {
        for (int i = 0; i < (int) Math.ceil(getMr() * getPopSize()); i++){
            for (int j = 0; j < 60; j++) {
                APEMutant[i][j] = APEMutant[i][j] + (Math.abs((this.actualData[j] - getTotalZiValueMutant()[i][j])/this.actualData[j]) * 100);
                APEMutant[i][j] = Double.parseDouble(decimalFormat.format(APEMutant[i][j]));
            }
        }
    }

    // MAPE
    @Override
    public void evaluateMAPE() {
        evaluateAPE();
        double[] tempTotalAPE = new double[getPopSize()];;
        for (int i = 0; i < getPopSize(); i++) {
            for (int j = 0; j < 60; j++) {
                tempTotalAPE[i] = tempTotalAPE[i] + APE[i][j];
            }
            MAPE[i] = tempTotalAPE[i]/60;
            MAPE[i] = Double.parseDouble(decimalFormat.format(MAPE[i]));
        }
    }
    @Override
    public void evaluateMAPEOffspring() {
        evaluateAPEOffspring();
        double[] tempTotalAPEOffspring = new double[(int) Math.ceil(getCr() * getPopSize())];;

        for (int i = 0; i < (int) Math.ceil(getCr() * getPopSize()); i++) {
            for (int j = 0; j < 60; j++) {
                tempTotalAPEOffspring[i] = tempTotalAPEOffspring[i] + APEOffspring[i][j];
            }
            MAPEOffspring[i] = tempTotalAPEOffspring[i]/60;
            MAPEOffspring[i] = Double.parseDouble(decimalFormat.format(MAPEOffspring[i]));
        }
    }
    @Override
    public void evaluateMAPEMutant() {
        evaluateAPEMutant();
        double[] tempTotalAPEMutant = new double[(int) Math.ceil(getMr() * getPopSize())];
        for (int i = 0; i < (int) Math.ceil(getMr() * getPopSize()); i++) {
            for (int j = 0; j < 60; j++) {
                tempTotalAPEMutant[i] = tempTotalAPEMutant[i] + APEMutant[i][j];
            }
            MAPEMutant[i] = tempTotalAPEMutant[i]/60;
            MAPEMutant[i] = Double.parseDouble(decimalFormat.format(MAPEMutant[i]));
        }
    }


    /*
     SETTER
     */

    // Upper value
    @Override
    public void setTemperatureColdUpperValue(double temperatureColdUpperValue) { this.temperatureColdUpperValue = temperatureColdUpperValue; }
    @Override
    public void setTemperatureWarmUpperValue(double temperatureWarmUpperValue) { this.temperatureWarmUpperValue = temperatureWarmUpperValue; }
    @Override
    public void setTemperatureHotUpperValue(double temperatureHotUpperValue) { this.temperatureHotUpperValue = temperatureHotUpperValue; }
    @Override
    public void setHumidityDryUpperValue(double humidityDryUpperValue) { this.humidityDryUpperValue = humidityDryUpperValue; }
    @Override
    public void setHumidityWetUpperValue(double humidityWetUpperValue) { this.humidityWetUpperValue = humidityWetUpperValue; }
    @Override
    public void setHumidityMoistUpperValue(double humidityMoistUpperValue) { this.humidityMoistUpperValue = humidityMoistUpperValue; }
    @Override
    public void setAirPressureLowUpperValue(double airPressureLowUpperValue) { this.airPressureLowUpperValue = airPressureLowUpperValue; }
    @Override
    public void setAirPressureMediumUpperValue(double airPressureMediumUpperValue) { this.airPressureMediumUpperValue = airPressureMediumUpperValue; }
    @Override
    public void setAirPressureHighUpperValue(double airPressureHighUpperValue) { this.airPressureHighUpperValue = airPressureHighUpperValue; }
    @Override
    public void setWindVelocityMediumUpperValue(double windVelocityMediumUpperValue) { this.windVelocityMediumUpperValue = windVelocityMediumUpperValue; }
    @Override
    public void setWindVelocityFastUpperValue(double windVelocityFastUpperValue) { this.windVelocityFastUpperValue = windVelocityFastUpperValue; }
    @Override
    public void setWindVelocityVeryFastUpperValue(double windVelocityVeryFastUpperValue) { this.windVelocityVeryFastUpperValue = windVelocityVeryFastUpperValue; }

    // Fuzzification variable
    @Override
    public void setFuzzTemperature(double[][][] fuzzTemperature) { this.fuzzTemperature = fuzzTemperature; }
    @Override
    public void setFuzzHumidity(double[][][] fuzzHumidity) { this.fuzzHumidity = fuzzHumidity; }
    @Override
    public void setFuzzAirPressure(double[][][] fuzzAirPressure) { this.fuzzAirPressure = fuzzAirPressure; }
    @Override
    public void setFuzzWindVelocity(double[][][] fuzzWindVelocity) { this.fuzzWindVelocity = fuzzWindVelocity; }
    @Override
    public void setFuzzTemperatureOffspring(double[][][] fuzzTemperatureOffspring) { this.fuzzTemperatureOffspring = fuzzTemperatureOffspring; }
    @Override
    public void setFuzzTemperatureMutant(double[][][] fuzzTemperatureMutant) { this.fuzzTemperatureMutant = fuzzTemperatureMutant; }
    @Override
    public void setFuzzHumidityOffspring(double[][][] fuzzHumidityOffspring) { this.fuzzHumidityOffspring = fuzzHumidityOffspring; }
    @Override
    public void setFuzzHumidityMutant(double[][][] fuzzHumidityMutant) { this.fuzzHumidityMutant = fuzzHumidityMutant;}
    @Override
    public void setFuzzAirPressureOffspring(double[][][] fuzzAirPressureOffspring) { this.fuzzAirPressureOffspring = fuzzAirPressureOffspring; }
    @Override
    public void setFuzzAirPressureMutant(double[][][] fuzzAirPressureMutant) { this.fuzzAirPressureMutant = fuzzAirPressureMutant;}
    @Override
    public void setFuzzWindVelocityOffspring(double[][][] fuzzWindVelocityOffspring) { this.fuzzWindVelocityOffspring = fuzzWindVelocityOffspring; }
    @Override
    public void setFuzzWindVelocityMutant(double[][][] fuzzWindVelocityMutant) { this.fuzzWindVelocityMutant = fuzzWindVelocityMutant;}

    @Override
    public void setAPE(double[][] tempAPE) {this.APE = tempAPE;}
    @Override
    public void setAPEOffspring(double[][] tempAPEOffspring) { this.APEOffspring = tempAPEOffspring;}
    @Override
    public void setAPEMutant(double[][] tempAPEMutant) { this.APEMutant = tempAPEMutant; }
    @Override
    public void setTotalZiValue(double[][] tempTotalZiValue) {this.totalZiValue = tempTotalZiValue;}
    @Override
    public void setTotalZiValueOffspring(double[][] tempTotalZiValueOffspring) { this.totalZiValueOffspring = tempTotalZiValueOffspring;}
    @Override
    public void setTotalZiValueMutant(double[][] tempTotalZiValueMutant) {this.totalZiValueMutant = tempTotalZiValueMutant;}
    @Override
    public void setTotalMiuMultiplyZi(double[][] tempTotalMiuMultiplyZi) {this.totalMiuMultiplyZi = tempTotalMiuMultiplyZi;}
    @Override
    public void setTotalMiuMultiplyZiOffspring(double[][] tempTotalMiuMultiplyZiOffspring) { this.totalMiuMultiplyZiOffspring = tempTotalMiuMultiplyZiOffspring; }
    @Override
    public void setTotalMiuMultiplyZiMutant(double[][] tempTotalMiuMultiplyZiMutant) { this.totalMiuMultiplyZiMutant = tempTotalMiuMultiplyZiMutant; }
    @Override
    public void setTotalFireStrength(double[][] tempTotalFireStrength) {this.totalFireStrength = tempTotalFireStrength;}
    @Override
    public void setTotalFireStrengthOffspring (double[][] tempTotalFireStrengthOffspring) { this.totalFireStrengthOffspring = tempTotalFireStrengthOffspring;}
    @Override
    public void setTotalFireStrengthMutant(double[][] tempTotalFireStrengthMutant) { this.totalFireStrengthMutant = tempTotalFireStrengthMutant;}




    /*
    GETTER
     */

    // Fire strength & upper value
    @Override
    public double getMuColdTemperature() { return Double.parseDouble(decimalFormat.format(muColdTemperature)); }
    @Override
    public double getMuWarmTemperature() { return Double.parseDouble(decimalFormat.format(muWarmTemperature)); }
    @Override
    public double getMuHotTemperature() { return Double.parseDouble(decimalFormat.format(muHotTemperature)); }
    @Override
    public double getMuDryHumidity() { return Double.parseDouble(decimalFormat.format(muDryHumidity)); }
    @Override
    public double getMuWetHumidity() { return Double.parseDouble(decimalFormat.format(muWetHumidity)); }
    @Override
    public double getMuMoistHumidity() { return Double.parseDouble(decimalFormat.format(muMoistHumidity)); }
    @Override
    public double getMuLowAirPressure() { return Double.parseDouble(decimalFormat.format(muLowAirPressure)); }
    @Override
    public double getMuMediumAirPressure() { return Double.parseDouble(decimalFormat.format(muMediumAirPressure)); }
    @Override
    public double getMuHighAirPressure() { return Double.parseDouble(decimalFormat.format(muHighAirPressure)); }
    @Override
    public double getMuMediumWindVelocity() { return Double.parseDouble(decimalFormat.format(muMediumWindVelocity));}
    @Override
    public double getMuFastWindVelocity() { return Double.parseDouble(decimalFormat.format(muFastWindVelocity));}
    @Override
    public double getMuVeryFastWindVelocity() { return Double.parseDouble(decimalFormat.format(muVeryFastWindVelocity)); }

    // Fire strength and zi value
    @Override
    public double[][][] getFireStrength() { return fireStrength; }
    @Override
    public double[][][] getFireStrengthOffspring() { return fireStrengthOffspring; }
    @Override
    public double[][][] getFireStrengthMutant() { return fireStrengthMutant; }
    @Override
    public double[][][] getZiValue() { return ziValue; }
    @Override
    public double[][][] getZiValueOffspring() { return ziValueOffspring; }
    @Override
    public double[][][] getZiValueMutant() { return ziValueMutant; }
    @Override
    public double[][][] getFuzzTemperature() {
        return fuzzTemperature;
    }
    @Override
    public double[][][] getFuzzTemperatureOffspring() { return fuzzTemperatureOffspring; }
    @Override
    public double[][][] getFuzzTemperatureMutant() { return fuzzTemperatureMutant; }
    @Override
    public double[][][] getFuzzHumidityOffspring() { return fuzzHumidityOffspring; }
    @Override
    public double[][][] getFuzzHumidityMutant() { return fuzzHumidityMutant; }
    @Override
    public double[][][] getFuzzAirPressureOffspring() { return  fuzzAirPressureOffspring; }
    @Override
    public double[][][] getFuzzAirPressureMutant() { return fuzzAirPressureMutant; }
    @Override
    public double[][][] getFuzzWindVelocityOffspring() {return fuzzWindVelocityOffspring;}
    @Override
    public double[][][] getFuzzWindVelocityMutant() {return fuzzWindVelocityMutant;}
    @Override
    public double[][][] getFuzzHumidity() {
        return fuzzHumidity;
    }
    @Override
    public double[][][] getFuzzAirPressure() {
        return fuzzAirPressure;
    }
    @Override
    public double[][][] getFuzzWindVelocity() {
        return fuzzWindVelocity;
    }
    @Override
    public double[][] getTotalFireStrength() {
        return totalFireStrength;
    }
    @Override
    public double[][] getTotalFireStrengthOffspring() { return totalFireStrengthOffspring; }
    @Override
    public double[][] getTotalFireStrengthMutant() { return totalFireStrengthMutant; }
    @Override
    public double[][] getTotalZiValue() { return totalZiValue; }
    @Override
    public double[][] getTotalZiValueOffspring() { return totalZiValueOffspring; }
    @Override
    public double[][] getTotalZiValueMutant() { return totalZiValueMutant; }
    @Override
    public double[][] getTotalMiuMultiplyZi() { return totalMiuMultiplyZi; }
    @Override
    public double[][] getTotalMiuMultiplyZiOffspring() { return totalMiuMultiplyZiOffspring; }
    @Override
    public double[][] getTotalMiuMultiplyZiMutant() { return totalMiuMultiplyZiMutant; }

    // APE & MAPE
    @Override
    public double[] getMAPE() { return MAPE; }
    @Override
    public double[] getMAPEOffspring() { return MAPEOffspring; }
    @Override
    public double[] getMAPEMutant() { return MAPEMutant; }
    @Override
    public double[][] getAPE() { return APE; }
    @Override
    public double[][] getAPEOffspring() { return APEOffspring; }
    @Override
    public double[][] getAPEMutant() { return APEMutant; }
}
