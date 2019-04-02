package com.pawszo.lata;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import javafx.scene.layout.BackgroundImage;

public class Lata extends Game {


    private Texture helicopterSheet, background;
    private Music heliMusic;
    private TextureRegion[] helicopterFrames;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Animation<TextureRegion> heliAni;
    private Rectangle helicopter;
    private float stateTime;


    @Override
    public void create() {
        helicopterSheet = new Texture(Gdx.files.internal("heliSprite.png"));

        heliMusic = Gdx.audio.newMusic(Gdx.files.internal("heli_1.wav"));
        heliMusic.play();
        heliMusic.setLooping(true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);


        helicopterFrames = new TextureRegion[5];
        TextureRegion[][] tmp = TextureRegion.split(helicopterSheet,
                helicopterSheet.getWidth() / 5,
                helicopterSheet.getHeight() / 1);
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                helicopterFrames[index++] = tmp[i][j];
            }
        }


        heliAni = new Animation<TextureRegion>(0.0025f, helicopterFrames);
        helicopter = new Rectangle();
        helicopter.width = 1;
        helicopter.height = 1;
        helicopter.x = 500;
        helicopter.y = 500;
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("winter1.jpeg"));
        stateTime = 0f;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame = heliAni.getKeyFrame(stateTime, true);
        fly(currentFrame);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(currentFrame, helicopter.x, helicopter.y);
        batch.end();
        camera.update();


    }

    @Override
    public void dispose() {
        batch.dispose();
        helicopterSheet.dispose();
        heliMusic.dispose();
    }
    /** handles steering and movement */
    private void fly(TextureRegion currentFrame) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) helicopter.y += 400 * Gdx.graphics.getDeltaTime();
        if (helicopter.y < 50) {

        } else {
            helicopter.y -= 100 * Gdx.graphics.getDeltaTime();
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                helicopter.x -= 300 * Gdx.graphics.getDeltaTime();
                if (currentFrame.isFlipX() == false) currentFrame.flip(true, false);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                helicopter.x += 300 * Gdx.graphics.getDeltaTime();
                if (currentFrame.isFlipX() == true) currentFrame.flip(true, false);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S)) helicopter.y -= 300 * Gdx.graphics.getDeltaTime();
        }
    }
}
