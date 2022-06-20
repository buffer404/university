#include "hal.h"
unsigned int leds_num[] = {GPIO_PIN_3, GPIO_PIN_4, GPIO_PIN_5,
                      GPIO_PIN_6, GPIO_PIN_8, GPIO_PIN_9,
                      GPIO_PIN_11, GPIO_PIN_12};
unsigned int sw_num[] = {GPIO_PIN_4, GPIO_PIN_8, GPIO_PIN_10, GPIO_PIN_12};
int umain(){
    
    while(1) {    
		if(HAL_GPIO_ReadPin(GPIOE, sw_num[0])==GPIO_PIN_SET){
			HAL_GPIO_WritePin(GPIOD, leds_num[0], GPIO_PIN_SET);
		} else{
			HAL_GPIO_WritePin(GPIOD, leds_num[0], GPIO_PIN_RESET);
		}
		
		if(HAL_GPIO_ReadPin(GPIOE, sw_num[1])==GPIO_PIN_SET){
			HAL_GPIO_WritePin(GPIOD, leds_num[1], GPIO_PIN_SET);
		} else{
			HAL_GPIO_WritePin(GPIOD, leds_num[1], GPIO_PIN_RESET);
		}
		
		if(HAL_GPIO_ReadPin(GPIOE, sw_num[2])==GPIO_PIN_SET){
			HAL_GPIO_WritePin(GPIOD, leds_num[2], GPIO_PIN_SET);
		} else{
			HAL_GPIO_WritePin(GPIOD, leds_num[2], GPIO_PIN_RESET);
		}
		
		if(HAL_GPIO_ReadPin(GPIOE, sw_num[3])==GPIO_PIN_SET){
			HAL_GPIO_WritePin(GPIOD, leds_num[3], GPIO_PIN_SET);
		} else{
			HAL_GPIO_WritePin(GPIOD, leds_num[3], GPIO_PIN_RESET);
		}
    }
    return 0;
}
