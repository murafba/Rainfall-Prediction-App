package geneticAlgorithm;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public abstract class GeneticAlgorithm {

    // Gene & Chromosome
    private double[] temperatureGene;
    private double[] humidityGene;
    private double[] airPressureGene;
    private double[] windVelocityGene;
    private double[][] chromosome;
    private int[] parent;
    private double[] fitnessValue;
    private double[][] tempParentChromosome;
    private double[][] tempOffspring;
    private double[][] mutant;

    // Genetic algorithm's parameters
    private int popSize;
    private int generationSize;
    private final int geneSize = 12;
    private double cr;
    private double mr;
    public DecimalFormat decimalFormat = new DecimalFormat("#.##");
    Random random = new Random();

    /*
    CONSTRUCTOR
     */
    public GeneticAlgorithm(int popSize, int generationSize, double cr, double mr) {
        this.temperatureGene = new double[3];
        this.humidityGene = new double[3];
        this.airPressureGene = new double[3];
        this.windVelocityGene = new double[3];
        this.popSize = popSize;
        this.chromosome = new double[popSize][this.geneSize];
        this.generationSize = generationSize;
        this.parent = new int[2];
        this.fitnessValue = new double[popSize];
        this.cr = cr;
        this.tempParentChromosome = new double[2][12];
        this.tempOffspring = new double[(int) Math.ceil(cr * popSize)][12];
        this.mr = mr;
        this.mutant = new double[(int) Math.ceil(mr * popSize)][12];
    }


    /**
     * Below is genetic algorithm's phases
     */

    /*
    Phase 1: Generate an initial population with popSize and geneSize!
     */
    public void generateInitialPopulation(){
        // Main loop => population size: represents how many chromosomes
        for (int i = 0; i < this.popSize; i++) {
            // Child loop 1 => assign each gene into part of chromosome
            for (int j = 0; j < 3; j++) {
                this.temperatureGene[j] = Double.parseDouble(decimalFormat.format(random.nextDouble(21,32)));
                this.humidityGene[j] = Double.parseDouble(decimalFormat.format(random.nextDouble(43,92)));
                this.airPressureGene[j] = Double.parseDouble(decimalFormat.format(random.nextDouble(1006,1017)));
                this.windVelocityGene[j] = Double.parseDouble(decimalFormat.format(random.nextDouble(1,7.5)));
            }
            Arrays.sort(this.temperatureGene); // Sort temperature gene
            Arrays.sort(this.humidityGene); // Sort humidity gene
            Arrays.sort(this.airPressureGene);  // Sort air pressure gene
            Arrays.sort(this.windVelocityGene); // Sort wind velocity gene

            // Child loop 2 => assign all genes into a full chromosome
            for (int j = 0; j < this.geneSize; j++){
                if (j <= 2){
                    this.chromosome[i][j] = this.temperatureGene[j];
                } else if (j <= 5) {
                    this.chromosome[i][j] = this.humidityGene[j-3];
                } else if (j <= 8) {
                    this.chromosome[i][j] = this.airPressureGene[j-6];
                } else {
                    this.chromosome[i][j] = this.windVelocityGene[j-9];
                }
            }
        }
    }

    /*
    Phase 2: Evaluate fitness value
     */
    public void evaluateFitnessValue() {
        for (int i = 0; i < getPopSize(); i++) {
            fitnessValue[i] = 1/getMAPE()[i];
        }
    }

    /*
    Phase 3: Roulette wheel selection to determine the parent
     */
    public void doRouletteSelection() {
        double tempTotalFitness = 0;
        double[] tempCumulative = new double[getPopSize()];
        int tempParent1 = -1;
        int tempParent2 = -1;


        for (int i = 0; i < getPopSize(); i++) {
            tempTotalFitness = tempTotalFitness + getFitnessValue()[i];
        }

        // Create cumulative value
        for (int i = 0; i < getPopSize(); i++) {
            if (i < 1)
                tempCumulative[i] = getFitnessValue()[i]/tempTotalFitness;
            else
                tempCumulative[i] = tempCumulative[i-1] + (getFitnessValue()[i]/tempTotalFitness);
        }


        if (getPopSize() <= 2) {
            this.parent[0] = 0;
            this.parent[1] = 1;
        } else {
            while (tempParent1 == tempParent2) {
                // Generate random value to pick parent
                double randomValue1 = random.nextDouble(0,1);
                double randomValue2 = random.nextDouble(0,1);

                // Pick parent from random value
                for (int i = 0; i < getPopSize(); i++) {
                    if (randomValue1 <= tempCumulative[i]) {
                        tempParent1 = i;
                        break;
                    }
                }

                for (int i = 0; i < getPopSize(); i++) {
                    if (randomValue2 <= tempCumulative[i]) {
                        tempParent2 = i;
                        break;
                    }
                }
            }
            this.parent[0] = tempParent1;
            this.parent[1] = tempParent2;
        }
    }

    /*
    Phase 4: Do the crossover
     */
    public void doCrossover() {
        System.out.println("Parent chromosome to:"); // testing
        System.out.println("[" + getParent()[0] + ", " + getParent()[1] + "]"); // testing: show chromosome parent
        System.out.println(); // testing: create new line
        System.out.println("Show parent's chromosomes: "); // testing

        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 12; k++) {
                if (j == 0) {
                    tempParentChromosome[j][k] = getChromosome()[getParent()[0]][k];
                } else {
                    tempParentChromosome[j][k] = getChromosome()[getParent()[1]][k];
                }
                System.out.print(tempParentChromosome[j][k] + "\t;"); // testing

            }
            System.out.println(); // testing
        }

        System.out.println(); // testing: create new line
        System.out.println("Show offspring:"); // testing

        for (int j = 0; j < (int) Math.ceil(getCr() * getPopSize()); j++) {
            for (int k = 0; k < 12; k++) {
                double ran = random.nextDouble(-0.25,1.25);
                tempOffspring[j][k] = tempParentChromosome[0][k] + (ran * (tempParentChromosome[1][k] - tempParentChromosome[0][k]));
                tempOffspring[j][k] = Double.parseDouble(decimalFormat.format(tempOffspring[j][k]));
                System.out.print(tempOffspring[j][k] + "\t"); // testing
//                System.out.print(decimalFormat.format(ran) + "\t"); // testing
            }
            System.out.println(); // testing
        }
    }

    /*
    Phase 5: Do the mutation
     */
    public void doMutation() {
        System.out.println(); // testing: create a new line
        System.out.println("Show mutant:"); // testing

        // Do mutation process
        for (int i = 0; i < (int) Math.ceil(getMr() * getPopSize()); i++) {
            // Generate random value to pick chromosome that will be mutated
            int mutationPicker = random.nextInt(0, getPopSize());
            System.out.println("Mutant from chromosome:" + "[" + mutationPicker + "]"); // testing

            for (int j = 0; j < 12; j++) {

                // Generate random value criteria
                double ran = random.nextDouble(-0.1,0.1);

                // Do the mutation process
                if (j < 3) {
                    mutant[i][j] = getChromosome()[mutationPicker][j] + (ran * (32-21));
                    mutant[i][j] = Double.parseDouble(decimalFormat.format(mutant[i][j]));
                } else if (j < 6) {
                    mutant[i][j] = getChromosome()[mutationPicker][j] + (ran * (92-43));
                    mutant[i][j] = Double.parseDouble(decimalFormat.format(mutant[i][j]));
                } else if (j < 9) {
                    mutant[i][j] = getChromosome()[mutationPicker][j] + (ran * (1017-1006));
                    mutant[i][j] = Double.parseDouble(decimalFormat.format(mutant[i][j]));
                } else {
                    mutant[i][j] = getChromosome()[mutationPicker][j] + (ran * (7.5-1));
                    mutant[i][j] = Double.parseDouble(decimalFormat.format(mutant[i][j]));
                }
                System.out.print(mutant[i][j] + "\t"); // testing
//                System.out.print(decimalFormat.format(ran) + "\t"); // testing
            }
            System.out.println(); // testing
        }
    }



    // GENETIC ALGORITHM'S GETTER
    public int getPopSize() { return popSize; }
    public double[] getFitnessValue() { return fitnessValue; }
    public double[][] getChromosome() { return chromosome; }
    public int getGenerationSize() { return generationSize; }
    public int[] getParent() { return parent; }
    public double getCr() { return cr; }
    public double getMr() { return mr; }
    public double[][] getTempOffspring() { return tempOffspring; }
    public double[][] getMutant() { return mutant; }

    // SETTER


    public void setChromosome(double[][] chromosome) { this.chromosome = chromosome; }

    /**
     * Below is FIS Tsukamoto's phases
     */

    /*
                PHASE 1: EVALUATE MU VALUE EACH VARIABLE
                 */
    // Temperature mu value
    public abstract void temperatureMuValue(double inputValue);

    // Humidity mu value
    public abstract void humidityMuValue(double inputValue);

    // Air Pressure mu value
    public abstract void airPressureMuValue(double inputValue);

    // Wind velocity mu value
    public abstract void windVelocityMuValue(double inputValue);

    // Evaluate each rule and produce fire strength
    public abstract void evaluateRule();

    public abstract void evaluateRuleOffspring();

    public abstract void evaluateRuleMutant();

    public abstract void evaluateRuleTsukamoto();

    /*
                PHASE 3: DO DEFUZZIFICATION
                 */
    public abstract void evaluateDefuzzifier();

    public abstract void evaluateDefuzzifierOffspring();

    public abstract void evaluateDefuzzifierMutant();

    public abstract void evaluateDefuzzifierTsukamoto();

    /*
                STEP 4: CALCULATE APE & MAPE
                 */
    // APE
    public abstract void evaluateAPE();

    public abstract void evaluateAPEOffspring();

    public abstract void evaluateAPEMutant();

    // MAPE
    public abstract void evaluateMAPE();

    public abstract void evaluateMAPEOffspring();

    public abstract void evaluateMAPEMutant();

    /*
                             SETTER
                             */
    // Upper value
    public abstract void setTemperatureColdUpperValue(double temperatureColdUpperValue);

    public abstract void setTemperatureWarmUpperValue(double temperatureWarmUpperValue);

    public abstract void setTemperatureHotUpperValue(double temperatureHotUpperValue);

    public abstract void setHumidityDryUpperValue(double humidityDryUpperValue);

    public abstract void setHumidityWetUpperValue(double humidityWetUpperValue);

    public abstract void setHumidityMoistUpperValue(double humidityMoistUpperValue);

    public abstract void setAirPressureLowUpperValue(double airPressureLowUpperValue);

    public abstract void setAirPressureMediumUpperValue(double airPressureMediumUpperValue);

    public abstract void setAirPressureHighUpperValue(double airPressureHighUpperValue);

    public abstract void setWindVelocityMediumUpperValue(double windVelocityMediumUpperValue);

    public abstract void setWindVelocityFastUpperValue(double windVelocityFastUpperValue);

    public abstract void setWindVelocityVeryFastUpperValue(double windVelocityVeryFastUpperValue);

    // Fuzzification variable
    public abstract void setFuzzTemperature(double[][][] fuzzTemperature);

    public abstract void setFuzzHumidity(double[][][] fuzzHumidity);

    public abstract void setFuzzAirPressure(double[][][] fuzzAirPressure);

    public abstract void setFuzzWindVelocity(double[][][] fuzzWindVelocity);

    public abstract void setFuzzTemperatureOffspring(double[][][] fuzzTemperatureOffspring);

    public abstract void setFuzzTemperatureMutant(double[][][] fuzzTemperatureMutant);

    public abstract void setFuzzHumidityOffspring(double[][][] fuzzHumidityOffspring);

    public abstract void setFuzzHumidityMutant(double[][][] fuzzHumidityMutant);

    public abstract void setFuzzAirPressureOffspring(double[][][] fuzzAirPressureOffspring);

    public abstract void setFuzzAirPressureMutant(double[][][] fuzzAirPressureMutant);

    public abstract void setFuzzWindVelocityOffspring(double[][][] fuzzWindVelocityOffspring);

    public abstract void setFuzzWindVelocityMutant(double[][][] fuzzWindVelocityMutant);


    public abstract void setAPE(double[][] tempAPE);

    public abstract void setAPEOffspring(double[][] tempAPEOffspring);

    public abstract void setAPEMutant(double[][] tempAPEMutant);

    public abstract void setTotalZiValue(double[][] tempTotalZiValue);

    public abstract void setTotalZiValueOffspring(double[][] tempTotalZiValueOffspring);

    public abstract void setTotalZiValueMutant(double[][] tempTotalZiValueMutant);

    public abstract void setTotalMiuMultiplyZi(double[][] tempTotalMiuMultiplyZi);

    public abstract void setTotalMiuMultiplyZiOffspring(double[][] tempTotalMiuMultiplyZiOffspring);

    public abstract void setTotalMiuMultiplyZiMutant(double[][] tempTotalMiuMultiplyZiMutant);

    public abstract void setTotalFireStrength(double[][] tempTotalFireStrength);

    public abstract void setTotalFireStrengthOffspring(double[][] tempTotalFireStrengthOffspring);

    public abstract void setTotalFireStrengthMutant(double[][] tempTotalFireStrengthMutant);

    /*
                                                                                    GETTER
                                                                                     */
    public abstract double getMuColdTemperature();

    public abstract double getMuWarmTemperature();

    public abstract double getMuHotTemperature();

    public abstract double getMuDryHumidity();

    public abstract double getMuWetHumidity();

    public abstract double getMuMoistHumidity();

    public abstract double getMuLowAirPressure();

    public abstract double getMuMediumAirPressure();

    public abstract double getMuHighAirPressure();

    public abstract double getMuMediumWindVelocity();

    public abstract double getMuFastWindVelocity();

    public abstract double getMuVeryFastWindVelocity();

    // Fire strength and zi value
    public abstract double[][][] getFireStrength();

    public abstract double[][][] getFireStrengthOffspring();

    public abstract double[][][] getFireStrengthMutant();

    public abstract double[][][] getZiValue();

    public abstract double[][][] getZiValueOffspring();

    public abstract double[][][] getZiValueMutant();

    public abstract double[][][] getFuzzTemperature();

    public abstract double[][][] getFuzzTemperatureOffspring();

    public abstract double[][][] getFuzzTemperatureMutant();

    public abstract double[][][] getFuzzHumidityOffspring();

    public abstract double[][][] getFuzzHumidityMutant();

    public abstract double[][][] getFuzzAirPressureOffspring();

    public abstract double[][][] getFuzzAirPressureMutant();

    public abstract double[][][] getFuzzWindVelocityOffspring();

    public abstract double[][][] getFuzzWindVelocityMutant();

    public abstract double[][][] getFuzzHumidity();

    public abstract double[][][] getFuzzAirPressure();

    public abstract double[][][] getFuzzWindVelocity();

    public abstract double[][] getTotalFireStrength();

    public abstract double[][] getTotalFireStrengthOffspring();

    public abstract double[][] getTotalFireStrengthMutant();

    public abstract double[][] getTotalZiValue();

    public abstract double[][] getTotalZiValueOffspring();

    public abstract double[][] getTotalZiValueMutant();

//    public abstract double[][] getTotalMiuMultiplyZi();

    public abstract double[][] getTotalMiuMultiplyZi();

    public abstract double[][] getTotalMiuMultiplyZiOffspring();

    public abstract double[][] getTotalMiuMultiplyZiMutant();

    // APE & MAPE
    public abstract double[] getMAPE();

    public abstract double[] getMAPEOffspring();

    public abstract double[] getMAPEMutant();

    public abstract double[][] getAPE();

    public abstract double[][] getAPEOffspring();

    public abstract double[][] getAPEMutant();
}
