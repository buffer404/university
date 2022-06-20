#include "hal.h"

int delay = 50;
int leds_num[] = {GPIO_PIN_3, GPIO_PIN_4, GPIO_PIN_5, GPIO_PIN_6, GPIO_PIN_8, GPIO_PIN_9, GPIO_PIN_11, GPIO_PIN_12};
unsigned int sw_nums[] ={GPIO_PIN_4, GPIO_PIN_8, GPIO_PIN_10, GPIO_PIN_12};
GPIO_PinState state[] = {GPIO_PIN_RESET, GPIO_PIN_RESET, GPIO_PIN_RESET, GPIO_PIN_RESET};


void del(){
		for(int i =0; i<10; i++){
			GPIO_PinState curState = HAL_GPIO_ReadPin(GPIOE, GPIO_PIN_15);
			
			if(curState == GPIO_PIN_RESET){
				HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_RESET);
				HAL_GPIO_WritePin(GPIOD, GPIO_PIN_15, GPIO_PIN_SET);
				HAL_Delay(delay);
				curState = HAL_GPIO_ReadPin(GPIOC, GPIO_PIN_15);
			
				while(curState == GPIO_PIN_SET){
					curState = HAL_GPIO_ReadPin(GPIOC, GPIO_PIN_15);
				}
			
				HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_SET);
				HAL_GPIO_WritePin(GPIOD, GPIO_PIN_15, GPIO_PIN_RESET);
				break;			
			}
			HAL_Delay(delay);
		}
}


int umain() {
	while(1){
		for(int r=0; r<4; r++){
			state[r] = HAL_GPIO_ReadPin(GPIOE, sw_nums[r]);
		}
		if(state[2] == GPIO_PIN_RESET && state[3] == GPIO_PIN_RESET && state[0] == GPIO_PIN_SET && state[1] == GPIO_PIN_SET){
			HAL_GPIO_WritePin(GPIOD, GPIO_PIN_14, GPIO_PIN_RESET);
			HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_SET);
			for(int k=0; k<4; k++){
				HAL_GPIO_WritePin(GPIOD, leds_num[k], GPIO_PIN_RESET);
			}
			for(int j=7; j>0; j--){
				HAL_GPIO_WritePin(GPIOD, leds_num[j], GPIO_PIN_SET);
				HAL_GPIO_WritePin(GPIOD, leds_num[j-1], GPIO_PIN_SET);
				del();
				HAL_GPIO_WritePin(GPIOD, leds_num[j], GPIO_PIN_RESET);
				HAL_GPIO_WritePin(GPIOD, leds_num[j-1], GPIO_PIN_RESET);
			}
			for(int l=1; l<6; l++){
				HAL_GPIO_WritePin(GPIOD, leds_num[l], GPIO_PIN_SET);
				HAL_GPIO_WritePin(GPIOD, leds_num[l+1], GPIO_PIN_SET);
				del();
				HAL_GPIO_WritePin(GPIOD, leds_num[l], GPIO_PIN_RESET);
				HAL_GPIO_WritePin(GPIOD, leds_num[l+1], GPIO_PIN_RESET);
			}
		}
		else{
			HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_RESET);
			HAL_GPIO_WritePin(GPIOD, GPIO_PIN_14, GPIO_PIN_SET);
			for(int i=0; i<4; i++){
				HAL_GPIO_WritePin(GPIOD, leds_num[i], state[i]);
			}
		}
	}
}



