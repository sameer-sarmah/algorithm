package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public class JobScheduling {
 
	private static List<JobInfo> jobs = getJobInfos();
	private static List<Integer> timeSlots = getTimeSlots();
	private static List<Integer> pickedJobsByTimeSlots = initializedPickedJobsByTimeSlots();
	private static int[][] jobValue = new int[ jobs.size() + 1 ][ timeSlots.size() + 1 ];
			
	public static void main(String[] args) {
		for(int jobIndex = 1 ;jobIndex <= jobs.size();jobIndex++) {
			JobInfo job = jobs.get(jobIndex - 1);
			for(int timeSlot : timeSlots) {
				if(timeSlot == job.endTimeSlot) {
					List<JobInfo> jobsToRemove =getPickedJobsInTimeSlots(job.startTimeSlot,job.endTimeSlot).stream()
								.filter((jobToRemove) -> jobToRemove.endTimeSlot <= timeSlot)
								.collect(Collectors.toList());
					int valueToSubstract = jobsToRemove.stream().mapToInt((jobInfo) -> jobInfo.value).sum();

					if(job.value + jobValue[jobIndex - 1][timeSlot] - valueToSubstract > jobValue[jobIndex - 1][timeSlot]) {
						jobValue[jobIndex][timeSlot] = job.value + jobValue[jobIndex - 1][timeSlot] - valueToSubstract;
						deallocateTimeSlots(job);
						allocateTimeSlots(job);
					}
				}
				else {
					jobValue[jobIndex][timeSlot] = Math.max(jobValue[jobIndex][timeSlot - 1],jobValue[jobIndex - 1][timeSlot]);
					pickedJobsByTimeSlots.set(timeSlot, 0);
				}
			}
		}
		System.out.println("maximum value: "+jobValue[jobs.size()][timeSlots.size()]);

	}
	

	private static List<JobInfo> getPickedJobsInTimeSlots(int startTimeSlot,int endTimeSlot) {		
		Set<JobInfo> jobsToRemove = new HashSet<JobInfo>();
		if(pickedJobsByTimeSlots.isEmpty()) {
			return jobsToRemove.stream().collect(Collectors.toList());
		}
		for(int timeSlot= startTimeSlot;timeSlot<= endTimeSlot; timeSlot++) {
			int jobIdScheduled = pickedJobsByTimeSlots.get(timeSlot);
			if( jobIdScheduled > 0) {
				jobsToRemove.add(jobs.get(jobIdScheduled - 1));
			}	
		}
		return jobsToRemove.stream().collect(Collectors.toList());
	}
	
	private static void allocateTimeSlots(JobInfo job) {
		for(int index= job.startTimeSlot;index<= job.endTimeSlot; index++) {
			pickedJobsByTimeSlots.set(index, job.id);
		}
	}
	
	private static void deallocateTimeSlots(JobInfo job) {
		for(int index= job.startTimeSlot;index<= job.endTimeSlot; index++) {
			pickedJobsByTimeSlots.set(index, 0);
		}
	}
	
	
	private static List<Integer> getTimeSlots(){
		List<Integer> timeSlots = IntStream.rangeClosed(1, 8).boxed().collect(Collectors.toList());
		return Collections.unmodifiableList(timeSlots);
	}
	
	private static List<Integer> initializedPickedJobsByTimeSlots(){
		List<Integer> pickedJobsByTimeSlots = new ArrayList<>();
		for(int index = 0;index <= timeSlots.size(); index++) {
			pickedJobsByTimeSlots.add(0);
		}
		return pickedJobsByTimeSlots;
	}
	
	private static List<JobInfo> getJobInfos(){
		List<JobInfo> jobs = new ArrayList<>();
		JobInfo job1 = new JobInfo(1, 1, 2, 50);
		JobInfo job2 = new JobInfo(2, 3, 5, 20);
		JobInfo job3 = new JobInfo(3, 1, 4, 100);
		JobInfo job4 = new JobInfo(4, 5, 8, 200);
		jobs.add(job1);
		jobs.add(job2);
		jobs.add(job3);
		jobs.add(job4);
		return Collections.unmodifiableList(jobs);
	}

	static class JobInfo{
		private int id,startTimeSlot,endTimeSlot;
		private int value;
		
		public int getStartTimeSlot() {
			return startTimeSlot;
		}

		public int getEndTimeSlot() {
			return endTimeSlot;
		}

		public int getValue() {
			return value;
		}

		public int getId() {
			return id;
		}

		public JobInfo(int id, int startTimeSlot, int endTimeSlot, int value) {
			super();
			this.id = id;
			this.startTimeSlot = startTimeSlot;
			this.endTimeSlot = endTimeSlot;
			this.value = value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			JobInfo other = (JobInfo) obj;
			if (id != other.id)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "[id=" + id + ", startTimeSlot=" + startTimeSlot + ", endTimeSlot=" + endTimeSlot
					+ ", value=" + value + "]";
		}	
		
		
		
	}
}
