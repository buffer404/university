#include <iostream>
#include <list>

using namespace std;

#define FIELD 1
#define FOREST 2
#define WATER 999999

#define SOUTH 0
#define NORTH 1
#define EAST 2
#define WEST 3

struct point {
    int type;
    int shortest_path_length;
    int path_direction;
    int x;
    int y;
};



int methodM() {
    int n, m, x_start, y_start, x_end, y_end;
    cin >> n >> m >> x_start >> y_start >> x_end >> y_end;
    x_start--;
    y_start--;
    x_end--;
    y_end--;

    struct point area[n][m];
    list<struct point> avalible;


    string line;
    for (int iter_y = 0; iter_y < n; iter_y++) {
        cin >> line;
        for (int iter_x = 0; iter_x < m; iter_x++) {
            auto c = line.at(iter_x);
            switch (c) {
                case '.':
                    area[iter_y][iter_x] = {FIELD, 999999, 0, iter_x, iter_y};
                    break;
                case 'W':
                    area[iter_y][iter_x] = {FOREST, 999999, 0, iter_x, iter_y};
                    break;
                case '#':
                    area[iter_y][iter_x] = {WATER, 999999, 0, iter_x, iter_y};
                    break;
            }
        }
    }

    avalible.push_back({0, 0, 0, x_start, y_start});
    area[y_start][x_start].shortest_path_length = 0;

    while (!avalible.empty()) {
        struct point cur_point = avalible.front();

//        cout << cur_point.x << " " << cur_point.y << endl;
//        if (cur_point.y == x_end && cur_point.x == y_end) {
//            cout << cur_point.shortest_path_length << endl;
//            string result;
//            while (x_end != x_start  y_end != y_start) {
//                int direction = area[x_end][y_end].path_direction;
//
//                switch (direction) {
//                    case SOUTH:
//                        x_end--;
//                        result.insert(0, "S");
//                        break;
//                    case NORTH:
//                        x_end++;
//                        result.insert(0, "N");
//                        break;
//                    case EAST:
//                        y_end--;
//                        result.insert(0, "E");
//                        break;
//                    case WEST:
//                        y_end++;
//                        result.insert(0, "W");
//                        break;
//                    default:
//                        break;
//                }
//            }
//            cout << result;
//            return 0;
//        }

        struct point next_point{};

        next_point = area[cur_point.y + 1][cur_point.x];
        if (cur_point.y != n - 1 &&
            next_point.type != WATER &&
            next_point.shortest_path_length > cur_point.shortest_path_length + next_point.type) {
            area[cur_point.y + 1][cur_point.x].shortest_path_length = cur_point.shortest_path_length + next_point.type;
            area[cur_point.y + 1][cur_point.x].path_direction = 0;
            avalible.push_back(area[cur_point.y + 1][cur_point.x]);
        }

        next_point = area[cur_point.y - 1][cur_point.x];
        if (cur_point.y != 0 &&
            next_point.type != WATER &&
            next_point.shortest_path_length > cur_point.shortest_path_length + next_point.type) {
            area[cur_point.y - 1][cur_point.x].shortest_path_length = cur_point.shortest_path_length + next_point.type;
            area[cur_point.y - 1][cur_point.x].path_direction = 1;
            avalible.push_back(area[cur_point.y - 1][cur_point.x]);
        }
        next_point = area[cur_point.y][cur_point.x + 1];
        if (cur_point.x != m - 1 &&
            next_point.type != WATER &&
            next_point.shortest_path_length > cur_point.shortest_path_length + next_point.type) {
            area[cur_point.y][cur_point.x+1].shortest_path_length = cur_point.shortest_path_length + next_point.type;
            area[cur_point.y ][cur_point.x+1].path_direction = 2;
            avalible.push_back(area[cur_point.y][cur_point.x+1]);
        }

        next_point = area[cur_point.y][cur_point.x - 1];
        if (cur_point.x != 0 &&
        next_point.type != WATER &&
        next_point.shortest_path_length > cur_point.shortest_path_length + next_point.type) {
            area[cur_point.y][cur_point.x-1].shortest_path_length = cur_point.shortest_path_length + next_point.type;
            area[cur_point.y][cur_point.x-1].path_direction = 3;
            avalible.push_back(area[cur_point.y + 1][cur_point.x-1]);
        }


        avalible.pop_front();
    }

    if (area[x_end][y_end].shortest_path_length < 999999) {
        cout << area[x_end][y_end].shortest_path_length << endl;
        string result;
        while (x_end != x_start || y_end != y_start) {
            int direction = area[x_end][y_end].path_direction;

            switch (direction) {
                case SOUTH:
                    x_end--;
                    result.insert(0, "S");
                    break;
                case NORTH:
                    x_end++;
                    result.insert(0, "N");
                    break;
                case EAST:
                    y_end--;
                    result.insert(0, "E");
                    break;
                case WEST:
                    y_end++;
                    result.insert(0, "W");
                    break;
                default:
                    break;
            }
        }
        cout << result;
        return 0;
    }


    cout << -1;
    return 0;
}


int main() {
    return methodM();
}