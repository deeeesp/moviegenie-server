package ru.stazaev.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.stazaev.api.controllers.intSwagger.ISelectionController;
import ru.stazaev.api.dto.request.SaveSelectionDto;
import ru.stazaev.api.dto.request.UpdateSelectionCoverDto;
import ru.stazaev.api.dto.response.ResponsePictureDto;
import ru.stazaev.api.dto.response.SelectionDto;
import ru.stazaev.api.services.SelectionService;

@RestController
@RequestMapping("/api/selection")
public class SelectionController implements ISelectionController {

    private final SelectionService selectionService;
    private final String SAVE_PATH = "/save";
    private final String FIND_BY_ID = "/{selection_id}";
    private final String FIND_BY_TAG = "/find-tag/{tag}";
    private final String DELETE_FILM_FROM_SELECTION = "/{selection_id}/delete/{film_id}";
    private final String ADD_FILM_TO_CUSTOM_SELECTION = "/{selection_id}/cust-sel/{film_id}";
    private final String DELETE_SELECTION_BY_ID = "/delete/{selection_id}";
    private final String DELETE_SELECTION_BY_TAG = "/delete-tag/{tag}";
    private final String UPDATE_COVER = "/cover-update";
    private final String GET_COVER = "/{selection_id}/cover";


    public SelectionController(SelectionService selectionService) {
        this.selectionService = selectionService;
    }

    @GetMapping(FIND_BY_ID)
    public ResponseEntity<SelectionDto> getSelection(@PathVariable("selection_id") long selectionId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(selectionService.getById(selectionId));
    }

    @GetMapping(FIND_BY_TAG)
    public ResponseEntity<SelectionDto> getSelectionByTag(@PathVariable String tag) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(selectionService.getByTag(tag));
    }

    @PostMapping(SAVE_PATH)
    public ResponseEntity<Void> saveSelection(@RequestBody SaveSelectionDto selectionDTO) {
        selectionService.saveNewSelection(selectionDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping(ADD_FILM_TO_CUSTOM_SELECTION)
    public ResponseEntity<Void> addToCustomSel(
            @PathVariable("selection_id") long selectionId,
            @PathVariable("film_id") long filmId,
            Authentication authentication) {
        selectionService.addFilmToCustomSelection(selectionId, filmId, authentication.getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping(DELETE_FILM_FROM_SELECTION)
    public ResponseEntity<Void> deleteFilmFromSelection(
            @PathVariable("film_id") long filmId,
            @PathVariable("selection_id") long selectionId,
            Authentication authentication) {
        selectionService.deleteFilmFromSelection(selectionId, filmId, authentication.getName());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping(DELETE_SELECTION_BY_ID)
    public ResponseEntity<Void> deleteSelectionById(
            @PathVariable("selection_id") long selectionId,
            Authentication authentication) {
        selectionService.deleteSelectionById(selectionId, authentication.getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping(DELETE_SELECTION_BY_TAG)
    public ResponseEntity<Void> deleteSelectionByTag(
            @PathVariable String tag,
            Authentication authentication) {
        selectionService.deleteSelectionByTag(tag, authentication.getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping(UPDATE_COVER)
    public ResponseEntity<Void> updateCover(
            @RequestBody UpdateSelectionCoverDto selectionCoverDto,
            Authentication authentication) {
        selectionService.updateSelectionCover(selectionCoverDto, authentication.getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping(GET_COVER)
    public ResponseEntity<ResponsePictureDto> getCover(
            @PathVariable("selection_id") long id) {
        var cover = selectionService.getSelectionCover(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cover);
    }
}
