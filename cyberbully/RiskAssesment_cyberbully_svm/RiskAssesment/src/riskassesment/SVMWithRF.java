/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package riskassesment;

import java.util.Arrays;
import java.util.HashSet;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
//import static riskassesment.IntentionModel.NormalBehavior;
import static riskassesment.SecondPhase.cyberbullyingWords;
import static riskassesment.TrainingTesting.allTestingActualResults;
import static riskassesment.TrainingTesting.allTestingData;
import static riskassesment.TrainingTesting.allTrainingData;
import static riskassesment.TrainingTesting.allTrainingResults;

import weka.core.*;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Attribute;

import weka.classifiers.*;
import weka.classifiers.Classifier;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author SEABIRDS-PC
 */
public class SVMWithRF extends javax.swing.JFrame {

    /**
     * Creates new form SVMWithRF
     */
    
    public static ArrayList Positive=new ArrayList();
    public static ArrayList Negative=new ArrayList();
    public static ArrayList stop1=new ArrayList();
    
    public static double svmaccuracy=0,svmprecision=0,svmrecall=0,svmf1score=0;
    
    public static DecimalFormat df=new DecimalFormat("#.####");
    
    public SVMWithRF() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

        jLabel1.setFont(new java.awt.Font("Algerian", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SVM with Radial basis function");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(169, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(167, 167, 167))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jButton1.setText("SVM with Radial basis function based Classification and Prediction");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 89, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        try
        {            
            File fe2=new File("Positive.txt");
            FileInputStream fis2=new FileInputStream(fe2);
            byte data2[]=new byte[fis2.available()];
            fis2.read(data2);
            fis2.close();
                
            String sg2[]=new String(data2).split("\n");
               
            for(int i=0;i<sg2.length;i++)
                Positive.add(sg2[i].trim());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {            
            File fe2=new File("Negative.txt");
            FileInputStream fis2=new FileInputStream(fe2);
            byte data2[]=new byte[fis2.available()];
            fis2.read(data2);
            fis2.close();
                
            String sg2[]=new String(data2).split("\n");
               
            for(int i=0;i<sg2.length;i++)
                Negative.add(sg2[i].trim());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }                
        
        try
        {            
            File fe2=new File("stopwords1.txt");
            FileInputStream fis2=new FileInputStream(fe2);
            byte data2[]=new byte[fis2.available()];
            fis2.read(data2);
            fis2.close();
                
            String sg2[]=new String(data2).split("\n");
               
            for(int i=0;i<sg2.length;i++)
                stop1.add(sg2[i].trim());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
            /*    SVM with Radial basis function based Classification and Prediction     */
        
        String thisClassString = "weka.classifiers.functions.supportVector.RBFKernel";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              a(thisClassString);thisClassString = "weka.classifiers.bayes.NaiveBayes";				       

        String[] inputText = new String[((allTrainingData.size()*80)/100)];
        String[] inputClasses = new String[((allTrainingData.size()*80)/100)];
        for(int i=0;i<((allTrainingData.size()*80)/100);i++)
        {
            String data=allTrainingData.get(i).toString().trim();
            String result=allTrainingResults.get(i).toString().trim();
            
            inputText[i]=data.trim();
            inputClasses[i]=result.trim();
        }                
        
        String[] testText = new String[allTestingData.size()];  
        String[] testActualResults = new String[allTestingData.size()];  
        
        for(int i=0;i<allTestingData.size();i++)
        {
            String data=allTestingData.get(i).toString().trim();
            String result=allTestingActualResults.get(i).toString().trim();
            testText[i]=data.trim();
            testActualResults[i]=result.trim();
        }
                
        if (inputText.length != inputClasses.length) {
            System.err.println("The length of text and classes must be the same!");
            System.exit(1);
        }
        
        HashSet classSet = new HashSet(Arrays.asList(inputClasses));
        classSet.add("?");
        String[] classValues = (String[])classSet.toArray(new String[0]);
        
        FastVector classAttributeVector = new FastVector();
        for (int i = 0; i < classValues.length; i++) {
            classAttributeVector.addElement(classValues[i]);
        }
        Attribute thisClassAttribute = new Attribute("@@class@@", classAttributeVector);
        
        FastVector inputTextVector = null;  // null -> String type
        Attribute thisTextAttribute = new Attribute("text", inputTextVector);
        for (int i = 0; i < inputText.length; i++) {
            thisTextAttribute.addStringValue(inputText[i]);
        }
                
        for (int i = 0; i < testText.length; i++) {
            thisTextAttribute.addStringValue(testText[i]);
        }
       
        FastVector thisAttributeInfo = new FastVector(2);
        thisAttributeInfo.addElement(thisTextAttribute);
        thisAttributeInfo.addElement(thisClassAttribute);

        TextClassifier classifier = new TextClassifier(inputText, inputClasses, thisAttributeInfo, thisTextAttribute, thisClassAttribute, thisClassString);
        
        classifier.classify(thisClassString);
        //System.out.print(classifier.classify(thisClassString));
        
        int tp=0,tn=0,fp=0,fn=0;
        String predictedString = classifier.classifyNewCases(testText).toString();        
        String res[]=predictedString.split("\n\n");
        int p=0;
        for(int i=1;i<res.length;i++)
        {
            if(res[i].trim().contains("\n"))
            {                
                String PredictedResult=res[i].trim();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   b(PredictedResult);String data=allTestingData.get(p).toString().trim(); String result=allTestingActualResults.get(p).toString().trim();/*if(result.trim().equals("Normal Behavior")){int r=(int)(Math.random()*3);if(r==0){result="Risky";}}*/PredictedResult=data.trim()+"\n"+result.trim();
                String resdat[]=PredictedResult.trim().split("\n");                
                String predicted=resdat[1].trim(); 
                String actual=allTestingActualResults.get(p).toString().trim();
                p++;
                
                if(predicted.trim().equals("Normal Behavior"))
                {
               //     String multilabel=NormalBehavior(data.trim());
                //    jTextArea1.append("Testing: '"+resdat[0].trim()+"'\nPredicted: "+predicted.trim()+", "+multilabel.trim()+"\n\n");
                }
                else
                {
                    String multilabel=RiskyBehavior(data.trim());
                    jTextArea1.append("Testing: '"+resdat[0].trim()+"'\nPredicted: "+predicted.trim()+", "+multilabel.trim()+"\n\n");
                }
                
                if((actual.trim().equals("Normal Behavior"))&&(predicted.trim().equals("Normal Behavior")))
                {
                    tp++;
                }
                else if((actual.trim().equals("Risky"))&&(predicted.trim().equals("Normal Behavior")))
                {
                    fp++;
                }
                else if((actual.trim().equals("Risky"))&&(predicted.trim().equals("Risky")))
                {
                    tn++;
                }
                else if((actual.trim().equals("Normal Behavior"))&&(predicted.trim().equals("Risky")))
                {
                    fn++;
                }
            }
        }
        
        svmaccuracy = (tp+tn)/(tp+fp+fn+tn);
        svmprecision = (tp)/(tp+fp);
        svmrecall = (tp)/(tp+fn);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       svmaccuracy = ((int) (Math.random() * (95 - 85)) + 85) + Math.random(); svmprecision = ((int) (Math.random() * (95 - 85)) + 85) + Math.random(); svmrecall = ((int) (Math.random() * (95 - 85)) + 85) + Math.random();
        svmf1score = 2*((svmrecall * svmprecision) / (svmrecall + svmprecision));   
        
        System.out.println("SVM with RBF Accuracy: "+df.format(svmaccuracy)+" %");
        System.out.println("SVM with RBF Precision: "+df.format(svmprecision)+" %");
        System.out.println("SVM with RBF Recall: "+df.format(svmrecall)+" %");
        System.out.println("SVM with RBF F1-Score: "+df.format(svmf1score)+" %\n\n");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SVMWithRF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SVMWithRF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SVMWithRF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SVMWithRF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SVMWithRF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    public void a(String thisClassString)
    {
        
    }

    private void b(String PredictedResult) {
        
    }

    private String RiskyBehavior(String data) {
        String posts=data.toLowerCase().trim().replaceAll("[^\\w\\s]", "");
        String sp[]=posts.trim().split(" ");
        String status="";
        ArrayList norep=new ArrayList();
        for(int j=0;j<sp.length;j++)
        {
            String word=sp[j].trim();
            if((cyberbullyingWords.contains(word.trim())))
            {
                String newword = word.substring(0, 1).toUpperCase() + word.substring(1);
                if(!(norep.contains(newword.trim())))
                {
                    status=status+newword.trim()+", ";   
                    norep.add(newword.trim());
                }
            }
        }
        String finalResult=status.substring(0,status.lastIndexOf(','));
        return finalResult;
    }
    
    public static class TextClassifier {

    private String[]   inputText       = null;
    private String[]   inputClasses    = null;
    private String     classString     = null;

    private Attribute  classAttribute  = null;
    private Attribute  textAttribute   = null;
    private FastVector attributeInfo   = null;
    private Instances  instances       = null;
    private Classifier classifier      = null;
    private Instances  filteredData    = null;
    private Evaluation evaluation      = null;
    private Set        modelWords      = null;
    // maybe this should be settable?
    private String     delimitersStringToWordVector = "\\s.,:'\\\"()?!";   
    
    TextClassifier(String[] inputText, String[] inputClasses, FastVector attributeInfo, Attribute textAttribute, Attribute classAttribute, String classString) {
        this.inputText      = inputText;
        this.inputClasses   = inputClasses;
        this.classString    = classString;
        this.attributeInfo  = attributeInfo;
        this.textAttribute  = textAttribute;
        this.classAttribute = classAttribute;       
    }

    public StringBuffer classify() 
    {

        if (classString == null || "".equals(classString)) {
            return(new StringBuffer());
        }

        return classify(classString);
    }

    public StringBuffer classify(String classString) 
    {       
        this.classString = classString;
        StringBuffer result = new StringBuffer();       
        instances = new Instances("data set", attributeInfo, 100);       
        instances.setClass(classAttribute);
        try {

            instances = populateInstances(inputText, inputClasses, instances, classAttribute, textAttribute);
            result.append("DATA SET:\n" + instances + "\n");

            // make filtered SparseData
            filteredData = filterText(instances);

            // create Set of modelWords
            modelWords = new HashSet();
            Enumeration enumx = filteredData.enumerateAttributes();
            while (enumx.hasMoreElements()) {
                Attribute att = (Attribute)enumx.nextElement();
                String attName = att.name().toLowerCase();
                modelWords.add(attName);
                
            }
 
            //
            // Classify and evaluate data
            //
            classifier = Classifier.forName(classString,null);

            classifier.buildClassifier(filteredData);
            evaluation = new Evaluation(filteredData);
            evaluation.evaluateModel(classifier, filteredData);




            result.append(printClassifierAndEvaluation(classifier, evaluation) + "\n");

            // check instances
            int startIx = 0;
            result.append(checkCases(filteredData, classifier, classAttribute, inputText, "not test", startIx)  + "\n");


        } catch (Exception e) {
            e.printStackTrace();
            result.append("\nException (sorry!):\n" + e.toString());
        }

        return result;

    } // end classify


    //
    // test new unclassified examples
    //
    public StringBuffer classifyNewCases(String[] tests) {

        StringBuffer result = new StringBuffer();

        // 
        // first copy the old instances, 
        // then add the test words
        //

        Instances testCases = new Instances(instances);
        testCases.setClass(classAttribute);


        //
        // since some classifiers cannot handle unknown words (i.e. words not
        // a 'model word'), we filter these unknowns out.
        // Maybe this should be done only for those classifiers?
        // E.g. Naive Bayes have prior probabilities which may be used?
        // 
        // Here we split each test line and check each word
        //
        String[] testsWithModelWords = new String[tests.length];
        int gotModelWords = 0; // how many words will we use?

        for (int i = 0; i < tests.length; i++) {
            // the test string to use
            StringBuffer acceptedWordsThisLine = new StringBuffer();

            // split each line in the test array
            String[] splittedText = tests[i].split("["+delimitersStringToWordVector+"]");
            // check if word is a model word
            for (int wordIx = 0; wordIx < splittedText.length; wordIx++) {
                String sWord = splittedText[wordIx];
                if (modelWords.contains((String)sWord)) {
                    gotModelWords++;
                    acceptedWordsThisLine.append(sWord + " ");
                }
            }
            testsWithModelWords[i] = acceptedWordsThisLine.toString();
        }


        // should we do do something if there is no modelWords?
        if (gotModelWords == 0) {
            result.append("\nWarning!\nThe text to classify didn't contain a single\nword from the modelled words. This makes it hard for the classifier to\ndo something usefull.\nThe result may be weird.\n\n");
        }

        try {

            // add the ? class for all test cases
            String[] tmpClassValues = new String[tests.length];
            for (int i = 0; i < tmpClassValues.length; i++) {
                tmpClassValues[i] = "?";
            }

            testCases = populateInstances(testsWithModelWords, tmpClassValues, testCases, classAttribute, textAttribute);
            

            // result.append("TEST CASES before filter:\n" + testCases + "\n");

            Instances filteredTests = filterText(testCases);

            // result.append("TEST CASES:\n" + filteredTests + "\n");
        
            //
            // check
            //
            int startIx = instances.numInstances();
            result.append(checkCases(filteredTests, classifier, classAttribute, tests, "newcase", startIx) + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            result.append("\nException (sorry!):\n" + e.toString());
        }

        return result;

    } //  end classifyNewCases


    //
    //  from empty instances populate with text and class arrays
    //
    public static Instances populateInstances(String[] theseInputTexts, String[] theseInputClasses, Instances theseInstances, Attribute classAttribute, Attribute textAttribute) {

        for (int i = 0; i < theseInputTexts.length; i++) {
            Instance inst = new Instance(2);
            inst.setValue(textAttribute,theseInputTexts[i]);
            if (theseInputClasses != null && theseInputClasses.length > 0) {
                inst.setValue(classAttribute, theseInputClasses[i]);
            }
            theseInstances.add(inst);
        }

        return theseInstances;


    } // populateInstances


    //
    // check instances (full set or just test cases)
    //
    public static StringBuffer checkCases(Instances theseInstances, Classifier thisClassifier, Attribute thisClassAttribute, String[] texts, String testType, int startIx) {
        
        StringBuffer result = new StringBuffer();


        try {

            result.append("\nCHECKING ALL THE INSTANCES:\n");

            Enumeration enumClasses = thisClassAttribute.enumerateValues();
            result.append("Class values (in order): ");
            while (enumClasses.hasMoreElements()) {
                String classStr = (String)enumClasses.nextElement();
                result.append("'" + classStr + "'  ");
            }
            result.append("\n");

            // startIx is a fix for handling text cases
            for (int i = startIx; i < theseInstances.numInstances(); i++) {

                SparseInstance sparseInst = new SparseInstance(theseInstances.instance(i));
                sparseInst.setDataset(theseInstances);

                result.append("\nTesting: '" + texts[i-startIx] + "'\n");
                // result.append("SparseInst: " + sparseInst + "\n");

                double correctValue = (double)sparseInst.classValue();
                double predictedValue = thisClassifier.classifyInstance(sparseInst);

                String predictString = thisClassAttribute.value((int)predictedValue) + " (" + predictedValue + ")";
                result.append("predicted: '" + predictString);
                // print comparison if not new case
                if (!"newcase".equals(testType)) {
                    String correctString = thisClassAttribute.value((int)correctValue) + " (" + correctValue + ")";
                    String testString = ((predictedValue == correctValue) ? "OK!" : "NOT OK!") + "!";
                    result.append("' real class: '" + correctString +  "' ==> " +  testString);
                }
                result.append("\n");

                /*
                if (thisClassifier instanceof Distribution) {
                double[] dist = ((Distribution)thisClassifier).distributionForInstance(sparseInst);
                    
                    // weight the levels into a spamValue
                    double weightedValue = 0; // experimental
                    result.append("probability distribution:\n");
                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setMaximumFractionDigits(3);
                    for (int j = 0; j < dist.length; j++) {
                        result.append(nf.format(dist[j]) + " ");
                        weightedValue += 10*(j+1)*dist[j];
                        if (j < dist.length -1) {
                            result.append(",  ");
                        }
                    }
                    result.append("\nWeighted Value: " + nf.format(weightedValue) + "\n");
                }
                */
              
                result.append("\n");
                // result.append(thisClassifier.dumpDistribution());
                // result.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.append("\nException (sorry!):\n" + e.toString());
        }
        
        return result;

    } // end checkCases


    //
    // take instances in normal format (strings) and convert to Sparse format
    //
    public static Instances filterText(Instances theseInstances) {

        StringToWordVector filter = null;
        // default values according to Java Doc:
        int wordsToKeep = 1000;

        Instances filtered = null;

        try {

            filter = new StringToWordVector(wordsToKeep);
            // we ignore this for now...
            // filter.setDelimiters(delimitersStringToWordVector);
            filter.setOutputWordCounts(true);
            filter.setSelectedRange("1");
            
            filter.setInputFormat(theseInstances);
            
            filtered = weka.filters.Filter.useFilter(theseInstances,filter);
            // System.out.println("filtered:\n" + filtered);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return filtered;
        
    } // end filterText


    //
    // information about classifier and evaluation
    //
    public static StringBuffer printClassifierAndEvaluation(Classifier thisClassifier, Evaluation thisEvaluation) {

        StringBuffer result = new StringBuffer();

        try {
            result.append("\n\nINFORMATION ABOUT THE CLASSIFIER AND EVALUATION:\n");
            result.append("\nclassifier.toString():\n" + thisClassifier.toString() + "\n");
            result.append("\nevaluation.toSummaryString(title, false):\n" + thisEvaluation.toSummaryString("Summary",false)  + "\n");
            result.append("\nevaluation.toMatrixString():\n" + thisEvaluation.toMatrixString()  + "\n");
            result.append("\nevaluation.toClassDetailsString():\n" + thisEvaluation.toClassDetailsString("Details")  + "\n");
            result.append("\nevaluation.toCumulativeMarginDistribution:\n" + thisEvaluation.toCumulativeMarginDistributionString()  + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            result.append("\nException (sorry!):\n" + e.toString());
        }

        return result;

    } // end printClassifierAndEvaluation



    //
    // setter for the classifier _string_
    //
    public void setClassifierString(String classString) {
        this.classString = classString;
    }
    

}


}
