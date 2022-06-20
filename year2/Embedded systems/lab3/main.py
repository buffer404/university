import numpy as np
import cv2


def process(frame):
    rect_size = 100
    h_sensivity = 20
    s_h = 255
    v_h = 255
    s_l = 50
    v_l = 50
    width, height, channels = frame.shape
    start_point = (int(height / 2 - rect_size / 2), int(width / 2 - rect_size / 2))
    end_point = (int(height / 2 + rect_size / 2), int(width / 2 + rect_size / 2))
    color = (255, 0, 0)
    thickness = 2
    rect = cv2.rectangle(frame, start_point, end_point, color, thickness)

    hsv_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    green_upper = np.array([60 + h_sensivity, s_h, v_h])
    green_lower = np.array([60 - h_sensivity, s_l, v_l])
    mask_frame = hsv_frame[start_point[1]:end_point[1] + 1, start_point[0]:end_point[0] + 1]
    mask_green = cv2.inRange(mask_frame, green_lower, green_upper)

    green_rate = np.count_nonzero(mask_green) / (rect_size * rect_size)

    org = end_point
    font = cv2.FONT_HERSHEY_SIMPLEX
    fontScale = 0.7

    if green_rate > 0.9:
        text = cv2.putText(rect, ' green ', org, font, fontScale, color, thickness, cv2.LINE_AA)
    else:
        text = cv2.putText(rect, ' not green ', org, font, fontScale, color, thickness, cv2.LINE_AA)

    av_hue = np.average(mask_frame[:, :, 0])
    av_sat = np.average(mask_frame[:, :, 1])
    av_val = np.average(mask_frame[:, :, 2])
    average = [int(av_hue), int(av_sat), int(av_val)]

    text = cv2.putText(rect, str(average) + " " + str(green_rate), (10, 50), font, fontScale, color, thickness,
                       cv2.LINE_AA)
    frame = text
    return frame


print('Press 4 to Quit the Application\n')

# Open Default Camera
cap = cv2.VideoCapture(0)  # gstreamer_pipeline(flip_method=4), cv2.CAP_GSTREAMER)

while (cap.isOpened()):
    # Take each Frame
    ret, frame = cap.read()

    # Flip Video vertically (180 Degrees)
    frame = cv2.flip(frame, 180)

    invert = process(frame)

    # Show video
    # cv2.imshow('Cam', frame)
    cv2.imshow('Inverted', invert)

    # Exit if "4" is pressed
    k = cv2.waitKey(1) & 0xFF
    if k == 52:  # ord 4
        # Quit
        print('Good Bye!')
        break

# Release the Cap and Video
cap.release()
cv2.destroyAllWindows()
