package com.darmi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class RankingScreen extends BaseScreen{
    private Stage stage;
    private Skin skin;
    private TextButton volver, jugar;
    private Image fondo;

    public RankingScreen(final MainGame game) {
        super(game);
        //instanciamos el fondo
        fondo=new Image(game.getManager().get("ranking.png", Texture.class));
        //definimos el escenario
        stage=new Stage(new FitViewport(640,360));
        //instanciamos las skin
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        //instanciamos el boton de volver al menu y volver a jugar
        volver = new TextButton("Volver al menu", skin);
        jugar = new TextButton("Volver a jugar", skin);

        //El boton volver nos llevara al menu principal
        volver.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.menuScreen);
            }
        });
        //El boton volver a jugar nos llevara a la pantalla de seleccion
        //de coche nuevo para volver a jugar
        jugar.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.seleccionScreen);
            }
        });

        //Asignamos tamaño y posicion a nuestros elementos que cargaremos en la pantalla
        fondo.setSize(630,450);
        jugar.setSize(120, 40);
        jugar.setPosition(90, 50);
        volver.setSize(120, 40);
        volver.setPosition(420, 50);

        //Añadimos todos estos recursos como actores del stage
        stage.addActor(fondo);
        stage.addActor(volver);
        stage.addActor(jugar);
    }
    @Override
    public void show() {
        //Se ejecuta solo cuando se inicia la pantalla
        //Procesa todos los procesos del stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        //Cuando cerramos la pantalla ponemos a null este InputProcessor
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        //eliminamos el stage y las skin
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        //pintamos el fondo de negro
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Actalizamos y pintamos el stage
        stage.act();
        stage.draw();
    }
}
