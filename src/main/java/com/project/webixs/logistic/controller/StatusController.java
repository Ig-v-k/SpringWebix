package com.project.webixs.logistic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("status")
public class StatusController extends ReadOnlyController<Status, Integer> {
  public StatusController(JpaRepository<Status, Integer> repo) {
	super(repo);
  }
}