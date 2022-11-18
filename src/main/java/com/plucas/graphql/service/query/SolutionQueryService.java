package com.plucas.graphql.service.query;

import com.plucas.graphql.datasource.problems.entity.Solution;
import com.plucas.graphql.datasource.problems.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionQueryService {

    @Autowired
    private SolutionRepository solutionRepository;


    public List<Solution> solutionsByKeyword(String keyword) {
        return solutionRepository.findByKeyword("%" + keyword +"%");
    }
}
