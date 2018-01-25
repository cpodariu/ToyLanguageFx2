package Controller;

import Exceptions.ExpressionException;
import Exceptions.FileException;
import Exceptions.HeapException;
import Exceptions.StatementException;
import Model.Statements.BaeStatements.IStmt;
import Model.PrgState;
import Repo.Repository;
import Utils.Interfaces.MyIList;
import Utils.MyFileReader;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    private Repository repository;
    private ExecutorService executor;
    
    public Controller()
    {
        this.repository = new Repository();
    }

    public Controller(Repository repository)
    {
        this.repository = repository;
    }
//
//    public void oneStep() throws ExpressionException, FileException, HeapException {
//        PrgState state = repository.getState();
//        IStmt stmt = state.getStack().pop();
//
//        stmt.execute(state);
//
//        state.getHeap().setContent(conservativeGarbageCollector(
//                state.getSymTable().values(),
//                state.getHeap().getContent()));
//
//        repository.logPrgStateExec();
//
//        System.out.print(state.toString());
//    }

    Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
        return heap.entrySet().stream().filter(e->symTableValues.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    
    public boolean shouldContinue(List<PrgState> prgList)
    {
        for (PrgState s: prgList)
            if (!s.getStack().isEmpty())
                return true;
        return false;
    }
    
    public void allSteps(boolean deleteAfter) {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList=removeCompletedPrg(repository.getPrgList());
        prgList.forEach(prg ->repository.logPrgStateExec(prg));
        while(shouldContinue(prgList)){
            try {
                oneStepForAllPrg(prgList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //remove the completed programs
            if (deleteAfter)
                prgList=removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository
        
        // update the repository state
        if (deleteAfter)
            repository.setPrgList(prgList);
        
    }
    
    
    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
//        prgList.forEach(prg ->repository.logPrgStateExec(prg));
        executor = Executors.newFixedThreadPool(2);
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep(p);}))
                .collect(Collectors.toList());
        
        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {   try { return future.get();
                        } catch (InterruptedException e) {
                            System.out.print("InterruptedExcception" + e.toString());
                        } catch (ExecutionException e) {
                            System.out.print("ExecutionExccption " + e.toString());
                        }
                            return null;    }
                    ).filter(Objects::nonNull).collect(Collectors.toList());
        
                    prgList.addAll(newPrgList);
        
                    prgList.forEach(prg ->repository.logPrgStateExec(prg));
                    //Save the current programs in the repository
                    repository.setPrgList(prgList);
    }


    public Repository getRepository()
    {
        return repository;
    }
    
    private List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }
}
