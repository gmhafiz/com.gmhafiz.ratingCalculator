package com.gmhafiz.ratingCalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import static java.lang.Math.round;

public class MainActivity extends Activity {

	private EditText myRatingText;
    private EditText opponentRatingText;
    private int coefficient;

    private double expectedScore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// A variable is used to refer to editText0
		myRatingText = (EditText) findViewById(R.id.editText0);
        opponentRatingText = (EditText) findViewById(R.id.editText1);
	}
	
	/** A method is called at button click because we assigned
	 * the name to the onClick property of the button
	 * 
	 */
	public void onClick (View view) {


		// assign variables to radio button result
//		switch (view.getId()) {
//            case R.id.editText0:
//
//                // If nothing is entered, pop up a toast
//                if (myRatingText.getText().length() == 0) {
//                    Toast.makeText(this, "Please enter your rating",
//                            Toast.LENGTH_LONG).show();
//                    return;
//                } else if (opponentRatingText.getText().length() == 0) {
//                    Toast.makeText(this, "Please enter your opponent rating",
//                            Toast.LENGTH_LONG).show();
//                    return;
//                }
//        }

        float myRating = Float.parseFloat(myRatingText.getText().toString());
        float opponentRating = Float.parseFloat(opponentRatingText.getText().toString());
        float ratingDiff = myRating - opponentRating;

        calcExpectedScore(ratingDiff);


        RadioButton k10Button = (RadioButton) findViewById(R.id.k10);
        RadioButton k20Button = (RadioButton) findViewById(R.id.k20);
        RadioButton k40Button = (RadioButton) findViewById(R.id.k40);

        if (k10Button.isChecked()) {
            coefficient = 10;
        } else if (k20Button.isChecked()) {
            coefficient = 20;
        } else if (k40Button.isChecked()) {
            coefficient = 40;
        }

        double newRating = 0;
        double ratingChange = 0;

        RadioButton winButton  = (RadioButton) findViewById(R.id.winButton);
        RadioButton drawButton = (RadioButton) findViewById(R.id.drawButton);
        RadioButton loseButton = (RadioButton) findViewById(R.id.loseButton);

        if (winButton.isChecked()) {
            //myRatingText.setText(String.valueOf(ConverterUtil.winsGame(wins)));

            int wins = 1;

            ratingChange = (wins - expectedScore) * coefficient;
            newRating = myRating + ratingChange;

        } else if (drawButton.isChecked()) {
            //myRatingText.setText(String.valueOf(ConverterUtil.drawGame(draws)));

            float draws = (float) 0.5;

            ratingChange = (draws - expectedScore) * coefficient;
            newRating = myRating + ratingChange;

        } else if (loseButton.isChecked()) {
            //myRatingText.setText(String.valueOf(ConverterUtil.loseGame(lose)));
            int lose = 0;

            ratingChange = (lose - expectedScore) * coefficient;
            newRating = myRating + ratingChange;
            newRating = round(newRating); // fixme: round to integer

        }

        EditText resultText = (EditText) findViewById(R.id.putResultHere);
        resultText.setText(String.valueOf(newRating));

        EditText kText = (EditText) findViewById(R.id.kText);
        kText.setText(String.valueOf(coefficient));

        EditText ratingDiffText = (EditText) findViewById(R.id.ratingDiffText);
        ratingDiffText.setText(String.valueOf(ratingDiff));

        EditText expectedScoreText = (EditText) findViewById(R.id.expectedScoreText);
        expectedScoreText.setText(String.valueOf(expectedScore));

        EditText ratingChangeText = (EditText) findViewById(R.id.ratingChangeText);
        ratingChangeText.setText(String.valueOf(ratingChange));

    } // End of onClick()

    private double calcExpectedScore(double ratingDiff) {
    /*
    FIDE ELO rating is based from normal distribution.
    There's no good linear formula to calculate FIDE rating change.
    Thus expected score is obtained from FIDE's table obtained from its handbook.
    http://www.fide.com/component/handbook/?id=172&view=article

    Official FIDE rating calculator: http://ratings.fide.com/calculator_rtd.phtml
    */

        if (ratingDiff <= -400) expectedScore = 0;
        else if (ratingDiff >= -399 && ratingDiff <= -392) expectedScore = 0.08;
        else if (ratingDiff >= -391 && ratingDiff <= -375) expectedScore = 0.09;
        else if (ratingDiff >= -374 && ratingDiff <= -358) expectedScore = 0.10;
        else if (ratingDiff >= -357 && ratingDiff <= -345) expectedScore = 0.11;
        else if (ratingDiff >= -344 && ratingDiff <= -329) expectedScore = 0.12;
        else if (ratingDiff >= -328 && ratingDiff <= -316) expectedScore = 0.13;
        else if (ratingDiff >= -315 && ratingDiff <= -303) expectedScore = 0.14;
        else if (ratingDiff >= -302 && ratingDiff <= -291) expectedScore = 0.15;
        else if (ratingDiff >= -290 && ratingDiff <= -279) expectedScore = 0.16;
        else if (ratingDiff >= -278 && ratingDiff <= -268) expectedScore = 0.17;
        else if (ratingDiff >= -267 && ratingDiff <= -257) expectedScore = 0.18;
        else if (ratingDiff >= -256 && ratingDiff <= -246) expectedScore = 0.19;
        else if (ratingDiff >= -245 && ratingDiff <= -236) expectedScore = 0.20;
        else if (ratingDiff >= -245 && ratingDiff <= -226) expectedScore = 0.21;
        else if (ratingDiff >= -225 && ratingDiff <= -216) expectedScore = 0.22;
        else if (ratingDiff >= -215 && ratingDiff <= -207) expectedScore = 0.23;
        else if (ratingDiff >= -206 && ratingDiff <= -198) expectedScore = 0.24;
        else if (ratingDiff >= -197 && ratingDiff <= -189) expectedScore = 0.25;
        else if (ratingDiff >= -188 && ratingDiff <= -180) expectedScore = 0.26;
        else if (ratingDiff >= -179 && ratingDiff <= -171) expectedScore = 0.27;
        else if (ratingDiff >= -170 && ratingDiff <= -163) expectedScore = 0.28;
        else if (ratingDiff >= -162 && ratingDiff <= -154) expectedScore = 0.29;
        else if (ratingDiff >= -153 && ratingDiff <= -146) expectedScore = 0.30;
        else if (ratingDiff >= -145 && ratingDiff <= -138) expectedScore = 0.31;
        else if (ratingDiff >= -137 && ratingDiff <= -130) expectedScore = 0.32;
        else if (ratingDiff >= -137 && ratingDiff <= -122) expectedScore = 0.33;
        else if (ratingDiff >= -121 && ratingDiff <= -114) expectedScore = 0.34;
        else if (ratingDiff >= -113 && ratingDiff <= -107) expectedScore = 0.35;
        else if (ratingDiff >= -106 && ratingDiff <= -99) expectedScore = 0.36;
        else if (ratingDiff >= -98 && ratingDiff <= -92) expectedScore = 0.37;
        else if (ratingDiff >= -91 && ratingDiff <= -84) expectedScore = 0.38;
        else if (ratingDiff >= -83 && ratingDiff <= -77) expectedScore = 0.39;
        else if (ratingDiff >= -76 && ratingDiff <= -69) expectedScore = 0.40;
        else if (ratingDiff >= -68 && ratingDiff <= -62) expectedScore = 0.41;
        else if (ratingDiff >= -61 && ratingDiff <= -54) expectedScore = 0.42;
        else if (ratingDiff >= -53 && ratingDiff <= -47) expectedScore = 0.43;
        else if (ratingDiff >= -46 && ratingDiff <= -40) expectedScore = 0.44;
        else if (ratingDiff >= -39 && ratingDiff <= -33) expectedScore = 0.45;
        else if (ratingDiff >= -32 && ratingDiff <= -26) expectedScore = 0.46;
        else if (ratingDiff >= -25 && ratingDiff <= -18) expectedScore = 0.47;
        else if (ratingDiff >= -17 && ratingDiff <= -11) expectedScore = 0.48;
        else if (ratingDiff >= -10 && ratingDiff <= 44) expectedScore = 0.49;
        else if (ratingDiff >= -3 && ratingDiff <= 3) expectedScore = 0.5;
        else if (ratingDiff >= 4 && ratingDiff <= 10) expectedScore = 0.51;
        else if (ratingDiff >= 11 && ratingDiff <= 17) expectedScore = 0.52;
        else if (ratingDiff >= 18 && ratingDiff <= 25) expectedScore = 0.53;
        else if (ratingDiff >= 26 && ratingDiff <= 32) expectedScore = 0.54;
        else if (ratingDiff >= 33 && ratingDiff <= 39) expectedScore = 0.55;
        else if (ratingDiff >= 40 && ratingDiff <= 46) expectedScore = 0.56;
        else if (ratingDiff >= 47 && ratingDiff <= 53) expectedScore = 0.57;
        else if (ratingDiff >= 54 && ratingDiff <= 61) expectedScore = 0.58;
        else if (ratingDiff >= 62 && ratingDiff <= 68) expectedScore = 0.59;
        else if (ratingDiff >= 69 && ratingDiff <= 76) expectedScore = 0.60;
        else if (ratingDiff >= 77 && ratingDiff <= 83) expectedScore = 0.61;
        else if (ratingDiff >= 84 && ratingDiff <= 91) expectedScore = 0.62;
        else if (ratingDiff >= 92 && ratingDiff <= 98) expectedScore = 0.63;
        else if (ratingDiff >= 99 && ratingDiff <= 106) expectedScore = 0.64;
        else if (ratingDiff >= 107 && ratingDiff <= 113) expectedScore = 0.65;
        else if (ratingDiff >= 114 && ratingDiff <= 121) expectedScore = 0.66;
        else if (ratingDiff >= 122 && ratingDiff <= 129) expectedScore = 0.67;
        else if (ratingDiff >= 130 && ratingDiff <= 137) expectedScore = 0.68;
        else if (ratingDiff >= 138 && ratingDiff <= 145) expectedScore = 0.69;
        else if (ratingDiff >= 146 && ratingDiff <= 153) expectedScore = 0.70;
        else if (ratingDiff >= 154 && ratingDiff <= 162) expectedScore = 0.71;
        else if (ratingDiff >= 163 && ratingDiff <= 170) expectedScore = 0.72;
        else if (ratingDiff >= 171 && ratingDiff <= 179) expectedScore = 0.73;
        else if (ratingDiff >= 180 && ratingDiff <= 188) expectedScore = 0.74;
        else if (ratingDiff >= 189 && ratingDiff <= 197) expectedScore = 0.75;
        else if (ratingDiff >= 198 && ratingDiff <= 206) expectedScore = 0.76;
        else if (ratingDiff >= 207 && ratingDiff <= 215) expectedScore = 0.77;
        else if (ratingDiff >= 216 && ratingDiff <= 225) expectedScore = 0.78;
        else if (ratingDiff >= 226 && ratingDiff <= 235) expectedScore = 0.79;
        else if (ratingDiff >= 236 && ratingDiff <= 245) expectedScore = 0.80;
        else if (ratingDiff >= 246 && ratingDiff <= 256) expectedScore = 0.81;
        else if (ratingDiff >= 257 && ratingDiff <= 267) expectedScore = 0.82;
        else if (ratingDiff >= 268 && ratingDiff <= 278) expectedScore = 0.83;
        else if (ratingDiff >= 279 && ratingDiff <= 290) expectedScore = 0.84;
        else if (ratingDiff >= 291 && ratingDiff <= 302) expectedScore = 0.85;
        else if (ratingDiff >= 303 && ratingDiff <= 315) expectedScore = 0.86;
        else if (ratingDiff >= 316 && ratingDiff <= 328) expectedScore = 0.87;
        else if (ratingDiff >= 329 && ratingDiff <= 344) expectedScore = 0.88;
        else if (ratingDiff >= 345 && ratingDiff <= 357) expectedScore = 0.89;
        else if (ratingDiff >= 358 && ratingDiff <= 374) expectedScore = 0.90;
        else if (ratingDiff >= 375 && ratingDiff <= 391) expectedScore = 0.91;
        else if (ratingDiff >= 392 && ratingDiff <= 399) expectedScore = 0.92;
        else if (ratingDiff >= 400) expectedScore = 1;

    return expectedScore;
    } // End of calcExpectedScore

} // End of MainActivity()
