package com.leetcode.chanllenge.leetcode243;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ProcessTasksUsingServers {
    private static class Server {
        private final int index;
        private final int weight;
        private int freeTime;

        Server(int index, int weight) {
            this.index = index;
            this.weight = weight;
            this.freeTime = 0;
        }

        void assignTask(int currentTime, int processTime) {
            freeTime = currentTime + processTime;
        }

        int getIndex() {
            return index;
        }

        int getWeight() {
            return weight;
        }

        int getFreeTime() {
            return freeTime;
        }
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        final PriorityQueue<Server> freeServers = new PriorityQueue<>(Comparator.comparingInt(Server::getWeight).thenComparingInt(Server::getIndex));
        for (int i = 0; i < servers.length; ++i) {
            freeServers.add(new Server(i, servers[i]));
        }

        final PriorityQueue<Server> processServers = new PriorityQueue<>(Comparator.comparingInt(Server::getFreeTime).thenComparingInt(Server::getWeight).thenComparingInt(Server::getIndex));

        final int[] result = new int[tasks.length];

        for (int i = 0; i < tasks.length; ++i) {

            while (!processServers.isEmpty() && processServers.peek().getFreeTime() <= i) {
				freeServers.add(processServers.poll());
			}

            if (freeServers.isEmpty()) {
                final Server early = processServers.poll();
                result[i] = early.getIndex();
                early.assignTask(early.getFreeTime(), tasks[i]);
                processServers.add(early);
            } else {
                final Server current = freeServers.poll();
                result[i] = current.getIndex();
                current.assignTask(i, tasks[i]);
                processServers.add(current);
            }
        }

        return result;
    }

    public static void main(String[] args) {
		final int[] servers = {338, 890, 301, 532, 284, 930, 426, 616, 919, 267, 571, 140, 716, 859, 980, 469, 628, 490,
				195, 664, 925, 652, 503, 301, 917, 563, 82, 947, 910, 451, 366, 190, 253, 516, 503, 721, 889, 964, 506,
				914, 986, 718, 520, 328, 341, 765, 922, 139, 911, 578, 86, 435, 824, 321, 942, 215, 147, 985, 619, 865};

		final int[] tasks = {773, 537, 46, 317, 233, 34, 712, 625, 336, 221, 145, 227, 194, 693, 981, 861, 317, 308,
				400, 2, 391, 12, 626, 265, 710, 792, 620, 416, 267, 611, 875, 361, 494, 128, 133, 157, 638, 632, 2, 158,
				428, 284, 847, 431, 94, 782, 888, 44, 117, 489, 222, 932, 494, 948, 405, 44, 185, 587, 738, 164, 356,
				783, 276, 547, 605, 609, 930, 847, 39, 579, 768, 59, 976, 790, 612, 196, 865, 149, 975, 28, 653, 417,
				539, 131, 220, 325, 252, 160, 761, 226, 629, 317, 185, 42, 713, 142, 130, 695, 944, 40, 700, 122, 992,
				33, 30, 136, 773, 124, 203, 384, 910, 214, 536, 767, 859, 478, 96, 172, 398, 146, 713, 80, 235, 176,
				876, 983, 363, 646, 166, 928, 232, 699, 504, 612, 918, 406, 42, 931, 647, 795, 139, 933, 746, 51, 63,
				359, 303, 752, 799, 836, 50, 854, 161, 87, 346, 507, 468, 651, 32, 717, 279, 139, 851, 178, 934, 233,
				876, 797, 701, 505, 878, 731, 468, 884, 87, 921, 782, 788, 803, 994, 67, 905, 309, 2, 85, 200, 368, 672,
				995, 128, 734, 157, 157, 814, 327, 31, 556, 394, 47, 53, 755, 721, 159, 843};

        new ProcessTasksUsingServers().assignTasks(servers, tasks);
    }
}
