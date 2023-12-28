# include "iGraphics.h"

#define SCREEN_WIDTH  1000
#define SCREEN_HEIGHT 600
#define BOARD_X 20
#define BOARD_Y 20
#define BOARD_WIDTH 960
#define BOARD_HEIGHT 500
#define WHITE 6
#define RED 1
#define GREEN 2
#define BLUE 3
#define BLACK 4
#define POINT 0
#define RECTANGLE 11
#define EMPTY_RECTANGLE 10
#define CIRCLE 21
#define EMPTY_CIRCLE 20
#define LINE 30

int X;
int Y;
int C;
int D;
int board[BOARD_WIDTH][BOARD_HEIGHT];
int currentColor = BLACK;
int currentShapeType = POINT;

typedef struct
{
    int x;
    int y;
    int w;
    int h;
    char text[20];
    int color;
} Button;

Button eraser,redtext,blacktext,redo,bluetext,greentext, colorRed,
       colorGreen, colorBlue, colorBlack, eraseall, rectangle, circle,line,
       recttext,cirtext,linetext,point1,point2,point3,point4;

int isMouseDown = FALSE;

void initButtons()
{
    eraser.x = 20;
    eraser.y = 525;
    eraser.w = 100;
    eraser.h = 30;
    eraser.color = WHITE;
    strcpy(eraser.text, "ERASER");

    /*redo.x = 100;
    redo.y = 560;
    redo.w = 55;
    redo.h = 30;
    strcpy(redo.text, "REDO");
    */

    eraseall.x = 20;
    eraseall.y = 560;
    eraseall.w = 100;
    eraseall.h = 30;
    strcpy(eraseall.text, "ERASE ALL");

    colorBlack.x = 170;
    colorBlack.y = 540;
    colorBlack.w = 50;
    colorBlack.h = 40;
    colorBlack.color = BLACK;

    blacktext.x=195-70;
    blacktext.y=525;
    blacktext.w=15;
    blacktext.h=5;
    strcpy(blacktext.text,"BLACK");


    colorRed.x = 250;
    colorRed.y = 540;
    colorRed.w = 50;
    colorRed.h = 40;
    colorRed.color = RED;

    redtext.x=280-70;
    redtext.y=525;
    redtext.w=15;
    redtext.h=5;
    strcpy(redtext.text,"RED");


    colorGreen.x = 330;
    colorGreen.y = 540;
    colorGreen.w = 50;
    colorGreen.h = 40;
    colorGreen.color = GREEN;

    greentext.x=355-70;
    greentext.y=525;
    greentext.w=15;
    greentext.h=5;
    strcpy(greentext.text,"GREEN");

    colorBlue.x = 410;
    colorBlue.y = 540;
    colorBlue.w = 50;
    colorBlue.h = 40;
    colorBlue.color = BLUE;

    bluetext.x=440-70;
    bluetext.y=525;
    bluetext.w=15;
    bluetext.h=5;
    strcpy(bluetext.text,"BLUE");



    point1.x = 490;
    point1.y = 560;
    point1.w = 30 ;
    point1.h =  20;
    strcpy (point1.text,"1");

    point2.x = 530;
    point2.y = 560;
    point2.w = 30 ;
    point2.h =  20;
    strcpy (point2.text,"5");

    point3.x = 570;
    point3.y = 560;
    point3.w = 30 ;
    point3.h =  20;
    strcpy (point3.text,"10");

    point4.x = 490;
    point4.y = 530;
    point4.w = 110 ;
    point4.h = 25;
    strcpy (point4.text,"MARKER SIZE");


    /*colorgrey.x=500;
    colorgrey.y=540;
    colorgrey.w=50;
    colorgrey.h=40;
    colorgrey.color=GREY;*/


    rectangle.x = 650;
    rectangle.y = 540;
    rectangle.w = 80;
    rectangle.h = 40;

    recttext.x=650;
    recttext.y=525;
    recttext.w=15;
    recttext.h=5;
    strcpy(recttext.text,"RECTANGLE");

    /*erectangle.x = 400;
    erectangle.y = 570;
    erectangle.w = 50;
    erectangle.h = 20;
    */
    circle.x = 760;
    circle.y = 540;
    circle.w = 40; // width is considered as radius
    circle.h = 40;

    cirtext.x=760;
    cirtext.y=525;
    cirtext.w=15;
    cirtext.h=5;
    strcpy(cirtext.text,"CIRCLE");

    /* ecircle.x = 600;
     ecircle.y = 540;
     ecircle.w = 50;
     ecircle.h = 20;
    */
    line.x = 830;
    line.y = 540;
    line.w = 60;
    line.h = 40;

    linetext.x = 830;
    linetext.y = 525;
    linetext.w = 60;
    linetext.h = 40;
    strcpy(linetext.text,"LINE");

}


void drawTextButton(Button button)
{
    iSetColor(255,255,255);
    iFilledRectangle(button.x, button.y, button.w, button.h);

    iSetColor(0,0,0);
    iText(button.x+10, button.y +10, button.text);
}
void insertText(Button button)
{
    iSetColor(0,0,0);
    iText(button.x+10+40,button.y,button.text);
}
void insertText2(Button button)
{
    iSetColor(0,0,0);
    iText(button.x+5,button.y,button.text);
}
void insertText3(Button button)
{
    iSetColor(0,0,0);
    iText(button.x-3,button.y,button.text);
}
void insertText4(Button button)
{
    iSetColor(0,0,0);
    iText(button.x+15,button.y,button.text);
}
void drawColorButton(Button button)
{
    if(button.color == BLACK)
    {
        iSetColor(0, 0, 0);
    }
    else if(button.color == RED)
    {
        iSetColor(255, 0, 0);
    }
    else if(button.color == GREEN)
    {
        iSetColor(0, 255, 0);
    }
    else if(button.color == BLUE)
    {
        iSetColor(0, 0, 255);
    }

    iFilledRectangle(button.x, button.y, button.w, button.h);
}

void drawShapeButton(Button button)
{
    iSetColor(0,0,0);
    iRectangle(rectangle.x,rectangle.y,rectangle.w,rectangle.h);

    iSetColor(0,0,0);
    iCircle(circle.x+circle.w/2,circle.y+circle.w/2,circle.w/2,1000);

    iSetColor(0,0,0);
    iLine(line.x,line.y,line.w+line.x,line.h+line.y);

    /* if(currentColor == RED)
     {
         iSetColor(255, 0, 0);
     }
     else if(currentColor == GREEN)
     {
         iSetColor(0, 255, 0);
     }
     else if(currentColor == BLUE)
     {
         iSetColor(0, 0, 255);
     }
     else
     {
         iSetColor(0, 0, 0);
     }*/

    /* if(type == FILLED_RECTANGLE)
     {
         iFilledRectangle(button.x, button.y, button.w, button.h);
         currentShapeType = FILLED_RECTANGLE;
     }
     else if(type == EMPTY_RECTANGLE)
     {
         iRectangle(button.x, button.y, button.w, button.h);
         currentShapeType = EMPTY_RECTANGLE;
     }
     else if(type == FILLED_CIRCLE)
     {
         iFilledCircle(button.x + button.w/2, button.y + button.w/2, button.w/2, 30);
         currentShapeType = FILLED_CIRCLE;
     }
     else if(type == EMPTY_CIRCLE)
     {
         iCircle(button.x + button.w/2, button.y + button.w/2, button.w/2, 30);
         currentShapeType = EMPTY_CIRCLE;
     }
     else if(type == LINE)
     {
         iLine(button.x, button.y, button.x + button.w, button.y + button.h);
         currentShapeType = LINE;
     }*/
}

void drawAllButton()
{
    insertText(redtext);
    insertText(bluetext);
    insertText(greentext);
    insertText(blacktext);
    insertText2(recttext);
    insertText3(cirtext);
    insertText4(linetext);
    drawTextButton(eraser);
    drawTextButton(point1);
    drawTextButton(point2);
    drawTextButton(point3);
    drawTextButton(point4);
    drawColorButton(colorRed);
    drawColorButton(colorGreen);
    drawColorButton(colorBlue);
    drawColorButton(colorBlack);
    drawShapeButton(circle);
    drawShapeButton(line);
    drawTextButton(eraseall);
}

void drawBG()
{
    iSetColor(190, 190, 190);
    iFilledRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
}


void drawCircle(int x, int y, int radius)
{
    for(int i = 0; i < BOARD_WIDTH; i++)
    {
        for(int j = 0; j < BOARD_HEIGHT; j++)
        {
            if((i-x)*(i-x) + (j-y)*(j-y) <= radius)
            {
                board[i][j] = currentColor;
            }
        }
    }
}

void drawBoard()
{
    iSetColor(255, 255, 255);
    iFilledRectangle(BOARD_X, BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT);

    for(int i = 0; i < BOARD_WIDTH; i++)
    {
        for(int j = 0; j < BOARD_HEIGHT; j++)
        {
            if(board[i][j] == BLACK)
            {
                iSetColor(0, 0, 0);
                iPoint(BOARD_X + i, BOARD_Y + j, 1);
            }
            else if(board[i][j] == RED)
            {
                iSetColor(255, 0, 0);
                iPoint(BOARD_X + i, BOARD_Y + j, 1);
            }
            else if(board[i][j] == GREEN)
            {
                iSetColor(0, 255, 0);
                iPoint(BOARD_X + i, BOARD_Y + j, 1);
            }

            else if(board[i][j] == BLUE)
            {
                iSetColor(0, 0, 255);
                iPoint(BOARD_X + i, BOARD_Y + j, 1);
            }
            else if(board[i][j] == WHITE)
            {
                iSetColor(255, 255, 255);
                iPoint(BOARD_X + i, BOARD_Y + j, 1);
            }
        }
    }
}

void iDraw()
{
    iClear();
    drawBG();
    drawBoard();
    drawAllButton();
}

int isPointInRectangle(int mx, int my, int x, int y, int w, int h)
{
    if(mx >= x && mx <= (x+w) && my >= y && my <= (y+h))
    {
        return 1;
    }

    return 0;
}


int isButtonClicked(int mx,int my, Button button)
{
    if(isPointInRectangle(mx, my, button.x, button.y, button.w, button.h))
    {
        return 1;
    }

    return 0;
}


int k=0;
void checkButtonClicks(int mx, int my)

{
    /*if(isButtonClicked(mx, my, undo))
    {
        printf("UNDO CLICKED\n");
    }
    else if(isButtonClicked(mx, my, redo))
    {
        printf("REDO CLICKED\n");
    }*/

    if(isButtonClicked(mx, my, eraser))
    {
        printf("ERASER CLICKED\n");

        currentColor = WHITE;
        k=15;
    }
    if(isButtonClicked(mx, my, colorRed))
    {
        printf("RED CLICKED\n");
        currentColor = RED;
    }

    if(isButtonClicked(mx, my, point1))
    {
        printf("point1 \n");
        k=1;

    }
    if(isButtonClicked(mx, my, point2))
    {
        printf("point2 \n");
        k=5;

    }

    if(isButtonClicked(mx, my, point3))
    {
        printf("point3 \n");
        k=10;
    }

    if(isButtonClicked(mx, my, colorBlue))
    {
        printf("BLUE CLICKED\n");
        currentColor = BLUE;
    }
    if(isButtonClicked(mx, my, colorGreen))
    {
        printf("GREEN CLICKED\n");
        currentColor = GREEN;
    }
    if(isButtonClicked(mx, my, colorBlack))
    {
        printf("BLACK CLICKED\n");
        currentColor = BLACK;
    }

    if(isButtonClicked(mx, my, rectangle))
    {
        printf("RECTANGLE\n");
        currentShapeType = RECTANGLE;
    }
    /*if(isButtonClicked(mx, my, colorgrey))
    {
        printf("GREY CLICKED \n");
        currentColor = WHITE;

    }*/
    /* if(isButtonClicked(mx, my, erectangle))
     {
         printf("EMPTY RECTANGLE\n");
     }*/
    if(isButtonClicked(mx, my, circle))
    {
        printf("CIRCLE\n");
        currentShapeType = CIRCLE;
    }
    /*if(isButtonClicked(mx, my, ecircle))
    {
        printf("EMPTY CIRCLE\n");
    }*/
    if(isButtonClicked(mx, my, line))
    {
        printf("LINE\n");
        currentShapeType = LINE ;
    }
    if(isButtonClicked(mx, my, eraseall))
    {
        printf("ERASEALL CLICKED\n");
        for( int i=0; i<=959; i++)
        {
            for( int j=0; j<=499; j++)
            {
                board[i][j]=0;

            }
        }
    }
}

void drawOnBoard(int mx, int my)
{
    drawCircle(mx - BOARD_X, my- BOARD_Y, k);
}

void drawShapeOnBoard(int mx, int my)
{

}

int currX1 = -1;
int currX2 = -1;
int currY1 = -1;
int currY2 = -1;
int currWidth, currHeight;
float radius = 0;

int Q=0;

void iMouse(int button, int state, int mx, int my)
{
    if(button == GLUT_LEFT_BUTTON && state == GLUT_UP)
    {
        checkButtonClicks(mx, my);
        isMouseDown = FALSE;
    }
    if(button == GLUT_LEFT_BUTTON && state == GLUT_DOWN)
    {
        checkButtonClicks(mx, my);
        isMouseDown = TRUE;

        if(isPointInRectangle(mx, my, BOARD_X, BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT))
        {
            if(currentShapeType == POINT)
            {
                drawOnBoard(mx, my);
            }
            else if(currentShapeType == RECTANGLE)
            {
                if(currX1 == -1)
                {
                    currX1 = mx - BOARD_X;
                    currY1 = my - BOARD_Y;
                }
                else if(currX2 == -1)
                {
                    currX2 = mx - BOARD_X;
                    currY2 = my - BOARD_Y;
                    currWidth = abs(currX2 - currX1);
                    currHeight = abs(currY2 - currY1);

                    if(currX2 < currX1)
                    {
                        currX1 = currX2;
                    }
                    if(currY2 < currY1)
                    {
                        currY1 = currY2;
                    }
                    for(int i = currX1; i < currX1 + currWidth; i++)
                    {
                        for(int j = currY1; j < currY1 + currHeight; j++)
                        {
                            board[i][j] = currentColor;
                        }
                    }
                    currentShapeType = POINT;
                    currX1 = -1;
                    currX2 = -1;
                    currY1 = -1;
                    currY2 = -1;
                }
            }
            else if(currentShapeType == CIRCLE)
            {
                if(currX1 == -1)
                {
                    currX1 = mx - BOARD_X;
                    currY1 = my - BOARD_Y;
                }
                else if(currX2 == -1)
                {
                    currX2 = mx - BOARD_X;
                    currY2 = my - BOARD_Y;

                    radius = (currX1 - currX2)*(currX1 - currX2) + (currY1 - currY2)*(currY1 - currY2);

                    drawCircle(currX1, currY1, radius);

                    currentShapeType = POINT;
                    currX1 = -1;
                    currX2 = -1;
                    currY1 = -1;
                    currY2 = -1;
                }
            }
            else if(currentShapeType == LINE)
            {
                if (currX1 == -1 )
                {
                    currX1 = mx - BOARD_X;
                    currY1 = my - BOARD_Y;
                }
                else if(currX2 == -1)
                {
                    currX2= mx - BOARD_X;
                    currY2=my - BOARD_Y;
                    currWidth = abs(currX2 - currX1);
                    currHeight = abs(currY2 - currY1);

                    float m = ((float )(currX1-currX2))/((float)(currY1-currY2));

                    if(currX2 < currX1)
                    {
                        currX1 = currX2;
                    }
                    if(currY2 < currY1)
                    {
                        currY1 = currY2;
                    }
                    for (float i= currX1 ; i<=currX1+currWidth; i+=.05)
                    {
                        for(float j = currY1; j<=currY1+currHeight; j+=.05)
                        {
                            float m1 = ((float)(i-currX2))/((float)(j-currY2));

                            if(m1+.005>= m && m1-.005<=m)
                            {
                                board[(int)i][(int)j]= currentColor;
                            }
                        }
                    }
                    currentShapeType = POINT;
                    currX1 = -1;
                    currX2 = -1;
                    currY1 = -1;
                    currY2 = -1;
                }
            }
        }
    }
}
void iMouseMove(int mx, int my)
{
    if(isPointInRectangle(mx, my, BOARD_X, BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT))
    {
        if(currentShapeType == POINT)
        {
            drawOnBoard(mx, my);
        }
    }
}
void iKeyboard(unsigned char key)
{
    if (key == 'q')
    {
        exit(0);
    }
}

void iSpecialKeyboard(unsigned char key)
{

    if (key == GLUT_KEY_END)
    {
        exit(0);
    }
}

int main()
{
    initButtons();
    iInitialize(SCREEN_WIDTH, SCREEN_HEIGHT, "Whiteboard");
    return 0;
}
