package org.example;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.Random;


public class GameMainClass implements ActionListener {
    private GamePanelButtonBar gamePanelButtonBar;
    private GamePanelPlayField gamePanelPlayField;
    private GamePanelStatusBar gamePanelStatusBar;
    private LinkedHashSet<KeyEnum> currentPressedKeys;


    private static boolean running = false;
    private static boolean paused = false;


    public GameMainClass() {
        GameImageCollection.Init(ClassLoader.getSystemClassLoader());
        JFrame frame = new JFrame("Fixed Timestep Game Loop Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(GameImageCollection.getMainImage());
        callConstructorFromAllPanels();
        backGroundColorAllPanels();
        addAllPanelsToFrame(frame);

        showFrameOnScreen(frame);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameMainClass();
        });
        Thread loopGameMusic = new Thread(() -> gameMusic());
        loopGameMusic.start();
        loopGameMusic.setName("loopGameMusic");
    }

    private void callConstructorFromAllPanels() {
        gamePanelStatusBar = new GamePanelStatusBar();
        gamePanelPlayField = new GamePanelPlayField(1000, 1000);
        gamePanelButtonBar = new GamePanelButtonBar(this);


    }

    private void backGroundColorAllPanels() {
        gamePanelStatusBar.setBackground(Color.GREEN);
        gamePanelPlayField.setBackground(Color.BLACK);
        gamePanelButtonBar.setBackground(Color.DARK_GRAY);
    }

    private void addAllPanelsToFrame(JFrame frame) {
        frame.add(gamePanelStatusBar, BorderLayout.NORTH);
        frame.add(gamePanelPlayField);
        frame.add(gamePanelButtonBar, BorderLayout.SOUTH);

        frame.pack();
    }

    private void showFrameOnScreen(JFrame frame) {
        frame.setVisible(true);
        Monitor.showOnScreen(1, frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == gamePanelButtonBar.getStartButton()) {
            running = !running;
            if (running) {
                gamePanelButtonBar.getStartButton().setText("Stop");
                gamePanelButtonBar.getPauseButton().setEnabled(true);
                runGameLoop();
            } else {
                gamePanelButtonBar.getStartButton().setText("Start");
                gamePanelButtonBar.getPauseButton().setEnabled(false);
            }
        } else if (s == gamePanelButtonBar.getPauseButton()) {
            paused = !paused;
            if (paused) {
                gamePanelButtonBar.getPauseButton().setText("Unpause");
            } else {
                gamePanelButtonBar.getPauseButton().setText("Pause");
            }
        } else if (s == gamePanelButtonBar.getQuitButton()) {
            System.exit(0);
        }
    }


    //Starts a new thread and runs the game loop in it.
    public void runGameLoop() {
        System.out.println("Before first thread graphics " + Thread.activeCount());
        Thread loopGameLogicAndGraphics = new Thread(() -> gameLoop());
        loopGameLogicAndGraphics.start();

        System.out.println("Before second thread music" + Thread.activeCount());





        System.out.println("After both threads " + Thread.activeCount());

    }

    private static void gameMusic() {
        System.out.println("starting in gameMusic()");

            try {
                InputStream is = GameMainClass.class.getResourceAsStream("/sound/metal_gear_title screen.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                while (true) {
                    while (running  & !paused) {
                        if (!clip.isRunning()) {
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }
                    if (clip.isRunning()) {
                        clip.stop();
                    }
                }

            } catch (LineUnavailableException lineUnavailableException) {
                System.out.println("In gameMusic LineUnavailableException");
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                System.out.println("In gameMusic UnsupportedAudioFileException");
            } catch (IOException ioException) {
                System.out.println("In gameMusic IOException");
            } catch (NullPointerException nullPointerException) {
                System.out.println("In gameMusic NullPointerException");
            } finally {
                System.out.println("Why we stop gameLoopMusic ?");
            }


    }


    //Only run this in another Thread!
    private void gameLoop() {
        System.out.println("Starting in gameLoop()");
        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 30.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_RENDER = 5;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we rendered.
        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);


        while (running) {
            currentPressedKeys = GameKeyListener.getCurrentPressedKeys();
            if (currentPressedKeys.contains(KeyEnum.KEY_ENTER)) {
                gamePanelPlayField.positionMainFigureToInitialPosition();
            }

            if (currentPressedKeys.contains(KeyEnum.KEY_LEFT)) {
                gamePanelPlayField.setMainFigureX(gamePanelPlayField.getMainFigureX() - gamePanelPlayField.getMainFigureHorizontalVelocity());
            }
            if (currentPressedKeys.contains(KeyEnum.KEY_RIGHT)) {
                gamePanelPlayField.setMainFigureX(gamePanelPlayField.getMainFigureX() + gamePanelPlayField.getMainFigureHorizontalVelocity());
            }
            if (currentPressedKeys.contains(KeyEnum.KEY_UP)) {
                gamePanelPlayField.setMainFigureY(gamePanelPlayField.getMainFigureY() - gamePanelPlayField.getMainFigureVerticalVelocity());
            }
            if (currentPressedKeys.contains(KeyEnum.KEY_DOWN)) {
                gamePanelPlayField.setMainFigureY(gamePanelPlayField.getMainFigureY() + gamePanelPlayField.getMainFigureVerticalVelocity());
            }
            gamePanelPlayField.repositionMainFigureInsideAllowedZone();

            double now = System.nanoTime();
            int updateCount = 0;

            if (!paused) {


                Random rn = new Random();
                int answer = rn.nextInt(10) + 1;

                gamePanelStatusBar.setLives(answer);

                //Do as many game updates as we need to, potentially playing catchup.
                while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
                    updateGame();
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                //If for some reason an update takes forever, we don't want to do an insane number of catchups.
                //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
                if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                //Render. To do so, we need to calculate interpolation for a smooth render.
                //float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
                drawGame(/*interpolation*/);
                lastRenderTime = now;

                //Update the frames we got.
                int thisSecond = (int) (lastUpdateTime / 1000000000);

                if (thisSecond > lastSecondTime) {
                    lastSecondTime = thisSecond;
                }

                //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
                while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
                    //allow the threading system to play threads that are waiting to run.
                    Thread.yield();

                    //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                    //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                    //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                    //On my OS it does not unpuase the game if i take this away
                    try {
                        Thread.sleep(1);
                    } catch (IllegalArgumentException illegalArgumentException) {
                        System.out.println("IllegalArgumentException in GameLoop");
                    } catch (InterruptedException interruptedException) {
                        System.out.println("InterruptedException in GameLoop");
                    }

                    now = System.nanoTime();
                }

            }
        }
        System.out.println("Ending GameLoop()");
    }

    private void updateGame() {
        gamePanelPlayField.update();
    }

    private void drawGame(/*float interpolation*/) {
        //gamePanelPlayField.setInterpolation(interpolation);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gamePanelPlayField.repaint();
            }
        });
    }
}



