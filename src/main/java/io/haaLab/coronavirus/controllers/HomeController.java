package io.haaLab.coronavirus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import io.haaLab.coronavirus.models.LocationStats;
import io.haaLab.coronavirus.services.CoronavirusService;

@Controller
public class HomeController {
	
	@Autowired
	CoronavirusService coronavirusService;
	
	
	@RequestMapping("/")
	public String home(Model model) {
		
		List<LocationStats> allStats = coronavirusService.getAllStats();
		
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);

		
		return "home";
	}

}
