//More like a system design
//让你实现两个支付接口， 第一个接口是记录下每笔存入的金额与时间， 接口二是返回过去60分钟所有存入的钱
//要求，时间颗粒度(granularity) 到秒; put, get调用频率很高，需要constant时间复杂度
//follow up: 优化用timestmap做key, 把popup one hour 的工作丢到process_loan
//这样就可以只maintain 1 hour timeframe 的数据。这样就都是constant

import java.util.Deque;
import java.util.LinkedList;

public class LoanAPI {
    int totalLoan;// record sum of all loans
    Deque<int[]> loanVolumes; // Using an array to store timestamp and total loan amount {timastamp, totalLoan}

    public LoanAPI(){
        totalLoan = 0;
        loanVolumes = new LinkedList<>();
    }

    public void processLoan(int timestamp, int amount){
        totalLoan += amount;
        loanVolumes.addLast(new int[]{timestamp, totalLoan});
    }

    // Return the loan volume at the current timestamp
    public int getLoanVolume(int timestamp){
        int reducedLoan = 0;//use a variety to recored total loan last poped out, which is loan need to be reduced
        while (!loanVolumes.isEmpty() && timestamp - loanVolumes.getFirst()[0] >= 3600) {
            reducedLoan = loanVolumes.getFirst()[1];
            loanVolumes.removeFirst();
        }
        return loanVolumes.getLast()[1] - reducedLoan;
    }

    public static void main(String[] args){
        LoanAPI loanApi = new LoanAPI();
        loanApi.processLoan(10, 5);
        loanApi.processLoan(22, 7);
        System.out.println(loanApi.getLoanVolume(22));//12
        loanApi.processLoan(3000, 8);
        loanApi.processLoan(3620, 5);
        System.out.println(loanApi.getLoanVolume(3620));//20
        loanApi.processLoan(3623, 10);
        System.out.println(loanApi.getLoanVolume(3623));//23
    }
}


//use fix space
//Time Complexity become constant
//Circular Queue using an array
public class LoanAPI2 {
    int totalLoan; 
    int[] timestamps; 
    int[] loanAmounts; 
    int front; //head of array
    int rear;//tail of array

    public LoanAPI2() {
        totalLoan = 0;
        timestamps = new int[3600];
        loanAmounts = new int[3600];
        front = rear = 0;
    }

    public void processLoan(int timestamp, int amount) {
        totalLoan += amount;
        //Checks if the circular array is full. 
        //If so, it removes the oldest entry by decrementing totalLoan and updating front.
        //maintain a fixed size array, prevent rear from out of boundary
        if ((rear + 1) % 3600 == front) {
            totalLoan -= loanAmounts[front];
            front = (front + 1) % 3600;
        }
        //Stores the timestamp and totalLoan at the rear index of the arrays.
        timestamps[rear] = timestamp;
        loanAmounts[rear] = totalLoan;
        //Updates rear.
        rear = (rear + 1) % 3600;
    }

    public int getLoanVolume(int timestamp) {
        int reducedLoan = 0;
        //Iterates through the circular array from front to rear, removing entries older than 3600 seconds.
        while (front != rear && timestamp - timestamps[front] >= 3600) {
            reducedLoan = loanAmounts[front];
            front = (front + 1) % 3600;
        }
        return totalLoan - reducedLoan;
    }

    public static void main(String[] args) {
        LoanAPI2 loanApi = new LoanAPI2();
        loanApi.processLoan(10, 5);
        loanApi.processLoan(22, 7);
        System.out.println(loanApi.getLoanVolume(22)); // 12
        loanApi.processLoan(3000, 8);
        loanApi.processLoan(3620, 5);
        System.out.println(loanApi.getLoanVolume(3620)); // 20
        loanApi.processLoan(3623, 10);
        System.out.println(loanApi.getLoanVolume(3623)); // 23
    }
}

