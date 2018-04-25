package algorithm.bnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JobAssignment {
    Map<Integer, Integer> job_person = new HashMap<>();
    int costMatrix[][] = { { 9, 2, 7, 8 }, { 6, 4, 3, 7 }, { 5, 8, 1, 8 }, { 7, 6, 9, 4 } };
    List<Integer> assignedJobs = new ArrayList<>();
    int best = Integer.MAX_VALUE;

    public static void main(String[] args) {
        JobAssignment j = new JobAssignment();
        j.jobAssignment();

    }

    private int computeBound(Map<Integer, Integer> map) {
        int bound = 0;
        Set<Integer> assignedJobs = new HashSet<>();
        assignedJobs.addAll(map.keySet());
        for (Map.Entry<Integer, Integer> e : this.job_person.entrySet()) {
            Integer job = e.getKey();
            Integer person = e.getValue();
            assignedJobs.add(job);
            bound+=costMatrix[person][job];
        }
      
        for (int i = 0; i < costMatrix.length; i++) {
            if (map.containsValue(i))
                continue;
            int[] jobs = costMatrix[i];
            List<Integer> jobList = Arrays.stream(jobs).boxed().collect(Collectors.toList());
            List<Integer> jobListCloned = new ArrayList<>();
            for (int num : jobList) {
                jobListCloned.add(num);
            }
            int min = findMinimun(assignedJobs, jobListCloned);
            int index = jobList.indexOf(min);
            assignedJobs.add(index);
            bound += min;
        }
        return bound;
    }

    private int findMinimun(Set<Integer> assignedJobs, List<Integer> jobList) {
        int min = Collections.min(jobList);
        int index = jobList.indexOf(min);
        if (!assignedJobs.contains(index)) {
            return min;
        } else {
            jobList.set(index, Integer.MAX_VALUE);
            return findMinimun(assignedJobs, jobList);
        }
    }

    public void jobAssignment() {
        int bounds;
        Map<Integer, Integer> job_person = new HashMap<>();
        for (int person = 0; person < costMatrix.length; person++) {
            for (int job = 0; job < costMatrix[person].length; job++) {
                if(this.job_person.containsKey(job))
                continue;
                job_person.put(job, person);
                bounds = computeBound(job_person);
                bounds = bounds + costMatrix[person][job];
                if (bounds <= best) {
                    best = bounds;
                    if(this.job_person.containsValue(person))
                    {
                        int personKey=-1;
                        for (Map.Entry<Integer, Integer> e : this.job_person.entrySet()) {
                            Integer key = e.getKey();
                            if(this.job_person.get(key)==person)
                                personKey= key;
                        }
                        this.job_person.remove(personKey);
                    }
                    if(this.job_person.containsKey(job))
                    {
                        this.job_person.remove(job); 
                    }
                    this.job_person.put(job, person);
                    System.out.println("Job " + job + " is assigned to person " + person + " with bounds " + bounds);
                }
                job_person.clear();
            }
        }

    }

}
