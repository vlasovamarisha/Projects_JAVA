/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.68).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Comment;
import io.swagger.model.CommentCreate;
import io.swagger.model.CommentUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-03-19T08:52:43.423157093Z[GMT]")
@Validated
public interface CommentsApi {

    @Operation(summary = "Создать новый комментарий", description = "", tags={ "Comments" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Комментарий успешно создан", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))),
        
        @ApiResponse(responseCode = "400", description = "Ошибка в переданных данных") })
    @RequestMapping(value = "/comments",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Comment> createComment(@Parameter(in = ParameterIn.DEFAULT, description = "Данные нового комментария", required=true, schema=@Schema()) @Valid @RequestBody CommentCreate body
);


    @Operation(summary = "Удалить комментарий по ID", description = "", tags={ "Comments" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "Комментарий успешно удален"),
        
        @ApiResponse(responseCode = "404", description = "Комментарий не найден") })
    @RequestMapping(value = "/comments/{commentId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteComment(@Parameter(in = ParameterIn.PATH, description = "Уникальный идентификатор комментария", required=true, schema=@Schema()) @PathVariable("commentId") String commentId
);


    @Operation(summary = "Получить комментарий по ID", description = "", tags={ "Comments" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Комментарий найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))),
        
        @ApiResponse(responseCode = "404", description = "Комментарий не найден") })
    @RequestMapping(value = "/comments/{commentId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Comment> getCommentById(@Parameter(in = ParameterIn.PATH, description = "Уникальный идентификатор комментария", required=true, schema=@Schema()) @PathVariable("commentId") String commentId
);


    @Operation(summary = "Получить список всех комментариев", description = "", tags={ "Comments" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Успешный ответ со списком комментариев", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comment.class)))) })
    @RequestMapping(value = "/comments",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Comment>> getComments();


    @Operation(summary = "Обновить комментарий по ID", description = "", tags={ "Comments" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Комментарий успешно обновлен", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))),
        
        @ApiResponse(responseCode = "400", description = "Ошибка в переданных данных"),
        
        @ApiResponse(responseCode = "404", description = "Комментарий не найден") })
    @RequestMapping(value = "/comments/{commentId}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Comment> updateComment(@Parameter(in = ParameterIn.PATH, description = "Уникальный идентификатор комментария", required=true, schema=@Schema()) @PathVariable("commentId") String commentId
, @Parameter(in = ParameterIn.DEFAULT, description = "Обновленные данные комментария", required=true, schema=@Schema()) @Valid @RequestBody CommentUpdate body
);

}

