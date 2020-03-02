package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlackjackActivity extends AppCompatActivity {

    private TextView bankerTextView;
    private TextView playerTextView;
    private Button dealButton;
    private Button showButton;
    private TextView resultTextView;
    private Button rematchButton;
    private TextView recordTextView;

    private Random random = new Random();
    private List<Integer> bankerCards = new ArrayList<>();
    private List<Integer> playerCards = new ArrayList<>();
    private int bankerPoint;
    private int playerPoint;

    private int winCount;
    private int loseCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackjack);
        findComponent();
        bindOnClick();
        onClickRematch();
    }

    private void findComponent() {
        bankerTextView = findViewById(R.id.bankerTextView);
        playerTextView = findViewById(R.id.playerTextView);
        dealButton = findViewById(R.id.dealButton);
        showButton = findViewById(R.id.showButton);
        resultTextView = findViewById(R.id.resultTextView);
        rematchButton = findViewById(R.id.rematchButton);
        recordTextView = findViewById(R.id.recordTextView);
    }

    private void bindOnClick() {
        dealButton.setOnClickListener(view -> onClickDeal());
        showButton.setOnClickListener(view -> onClickShow());
        rematchButton.setOnClickListener(view -> onClickRematch());
    }

    private void onClickDeal() {
        int nextBankerCard = random.nextInt(13) + 1;
        int nextPlayerCard = random.nextInt(13) + 1;
        bankerCards.add(nextBankerCard);
        playerCards.add(nextPlayerCard);
        bankerPoint = autoBestPoint(bankerCards);
        playerPoint = autoBestPoint(playerCards);
        if (bankerCards.size() == 1) {
            bankerTextView.append(toCard(nextBankerCard) + " ");
        } else {
            bankerTextView.append("? ");
        }
        playerTextView.append(toCard(nextPlayerCard) + " ");
        if (playerPoint > 21) {
            onClickShow();
        }
    }

    private void onClickShow() {
        boolean win = true;
        if (playerPoint > 21) {
            win = false;
        } else if (bankerPoint < 21 && playerPoint <= bankerPoint) {
            win = false;
        }
        StringBuilder builder = new StringBuilder("庄家手牌：");
        for (Integer point : bankerCards) {
            builder.append(toCard(point)).append(" ");
        }
        bankerTextView.setText(builder.toString());
        dealButton.setVisibility(View.INVISIBLE);
        showButton.setVisibility(View.INVISIBLE);
        String resultText = "庄家点数:" + bankerPoint + ", 你的点数:" + playerPoint + ", 你";
        if (win) {
            resultText += "赢了";
            winCount++;
        } else {
            resultText += "输了";
            loseCount++;
        }
        resultTextView.setText(resultText);
        resultTextView.setVisibility(View.VISIBLE);
        rematchButton.setVisibility(View.VISIBLE);
        String record = "赢:" + winCount + " 输:" + loseCount;
        recordTextView.setText(record);
    }

    private void onClickRematch() {
        reset();
        onClickDeal();
        onClickDeal();
    }

    private String toCard(int point) {
        if (point == 1) {
            return "A";
        }
        if (point == 11) {
            return "J";
        }
        if (point == 12) {
            return "Q";
        }
        if (point == 13) {
            return "K";
        }
        return "" + point;
    }

    private void reset() {
        bankerCards.clear();
        playerCards.clear();
        bankerPoint = 0;
        playerPoint = 0;
        bankerTextView.setText("庄家手牌：");
        playerTextView.setText("你的手牌：");
        dealButton.setVisibility(View.VISIBLE);
        showButton.setVisibility(View.VISIBLE);
        resultTextView.setText("");
        resultTextView.setVisibility(View.INVISIBLE);
        rematchButton.setVisibility(View.INVISIBLE);
    }

    private int autoBestPoint(List<Integer> cards) {
        int point = 0;
        int blackjackCount = 0;
        for (Integer card : cards) {
            if (card == 1) {
                blackjackCount++;
                point++;
            } else if (card < 10) {
                point += card;
            } else {
                point += 10;
            }
        }
        while (point <= 11 && blackjackCount > 0) {
            point += 10;
            blackjackCount--;
        }
        return point;
    }

}
