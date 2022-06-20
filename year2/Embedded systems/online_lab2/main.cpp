#include "hal.h"

int i = 0;
int delay = 500;
int leds_num[] = {GPIO_PIN_3, GPIO_PIN_4, GPIO_PIN_5,
GPIO_PIN_6, GPIO_PIN_8, GPIO_PIN_9,
GPIO_PIN_11, GPIO_PIN_12};
unsigned int sw_num[] = {GPIO_PIN_4, GPIO_PIN_8, GPIO_PIN_10, GPIO_PIN_12};

int get_value(){
    int result = 0;
		if(HAL_GPIO_ReadPin(GPIOE, sw_num[0])==GPIO_PIN_SET){
			result+=8;
		}
		if(HAL_GPIO_ReadPin(GPIOE, sw_num[1])==GPIO_PIN_SET){
			result+=4;
		}
		if(HAL_GPIO_ReadPin(GPIOE, sw_num[2])==GPIO_PIN_SET){
			result+=2;
		}
		if(HAL_GPIO_ReadPin(GPIOE, sw_num[3])==GPIO_PIN_SET){
			result++;
		} 
    return result;
}

void TIM6_IRQ_Handler(){
    for(int i = 0; i<8; i++){
            HAL_GPIO_WritePin(GPIOD, leds_num[i], GPIO_PIN_RESET);
    }
    if(i==0 || i==6){
        HAL_GPIO_WritePin(GPIOD, leds_num[3], GPIO_PIN_SET);
        HAL_GPIO_WritePin(GPIOD, leds_num[4], GPIO_PIN_SET);
    } else if(i==1 || i==5){
        HAL_GPIO_WritePin(GPIOD, leds_num[2], GPIO_PIN_SET);
        HAL_GPIO_WritePin(GPIOD, leds_num[3], GPIO_PIN_SET);
        HAL_GPIO_WritePin(GPIOD, leds_num[4], GPIO_PIN_SET);
        HAL_GPIO_WritePin(GPIOD, leds_num[5], GPIO_PIN_SET);
    } else if(i==2 || i==4){
        HAL_GPIO_WritePin(GPIOD, leds_num[1], GPIO_PIN_SET);
        HAL_GPIO_WritePin(GPIOD, leds_num[2], GPIO_PIN_SET);
        HAL_GPIO_WritePin(GPIOD, leds_num[3], GPIO_PIN_SET);
        HAL_GPIO_WritePin(GPIOD, leds_num[4], GPIO_PIN_SET);
        HAL_GPIO_WritePin(GPIOD, leds_num[5], GPIO_PIN_SET);
        HAL_GPIO_WritePin(GPIOD, leds_num[6], GPIO_PIN_SET);
    } else if(i==3){
        for(int i = 0; i<8; i++){
            HAL_GPIO_WritePin(GPIOD, leds_num[i], GPIO_PIN_SET);
        }
    }
    i++;
    if(i == 8){
        i=0;
        int offset = 100;
        offset*=get_value();
        delay = 500;
        delay+=offset;
        WRITE_REG(TIM6_ARR, delay);
    }
}

int umain(){
	registerTIM6_IRQHandler(TIM6_IRQ_Handler);
	__enable_irq();
	WRITE_REG(TIM6_DIER, TIM_DIER_UIE);
	WRITE_REG(TIM6_PSC, 0);
    int offset = 100;
    offset*=get_value();

    delay+=offset;
    WRITE_REG(TIM6_ARR, delay);

    for(int j = 0; j<8; j++){
        WRITE_REG(TIM6_CR1, TIM_CR1_CEN);
        HAL_Delay(delay);
    }

	return 0;
}