package sport.competitions.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sport.competitions.service.CompetitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.security.Principal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public/rest/competitions")
@Api(value = "CompetitionsAPI", description = "API for accessing competitions")
public class CompetitionRestService {

    @Autowired
    private CompetitionService competitionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "View a list of all available competitions", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<Object> browse() {
        return ResponseEntity.ok(competitionService.listAll());
    }

    @ApiOperation(value = "Remove a competition by ID", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully removed"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id, Principal principal) {

        if (principal == null) {
            throw new ForbiddenException();
        }

        competitionService.delete(id);
    }

    @RequestMapping(value = "/mockdelete/{id}", method = RequestMethod.GET)
    public void mockdelete(@PathVariable("id") Integer id, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        competitionService.delete(id);
    }

    @RequestMapping(value = "/{number}/{name}/{date}/{place}", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@PathVariable("number") Integer number, @PathVariable("name") String name,
                                      @PathVariable("date") String date, @PathVariable("place") String place, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        return ResponseEntity.ok(competitionService.add(number, name, date, place));
    }

    @ApiOperation(value = "Find a competition by number", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    public ResponseEntity<Object> findByNumber(@PathVariable("number") Integer number) {
        return ResponseEntity.ok(competitionService.findByNumber(number));
    }
}